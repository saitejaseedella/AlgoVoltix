package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import com.algovoltix.evbooking.entity.Payment;
import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.repository.PaymentRepository;
import com.algovoltix.evbooking.repository.WalletRepository;
import com.algovoltix.evbooking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final WalletRepository walletRepository;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        if (request.getWalletId() == null) {
            log.error("Payment creation failed: walletId is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "walletId must not be null");
        }
        if (request.getPaymentMode() == null) {
            log.error("Payment creation failed: paymentMode is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "paymentMode must not be null");
        }
        Wallet wallet = walletRepository.findById(request.getWalletId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
        Payment payment = new Payment();
        payment.setWallet(wallet);
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getStatus());
        Payment saved = paymentRepository.save(payment);
        log.info("Created Payment: {}", saved.getPaymentId());
        return PaymentResponse.builder()
            .paymentId(saved.getPaymentId())
            .walletId(wallet.getWalletId())
            .paymentMode(saved.getPaymentMode())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        return PaymentResponse.builder()
            .paymentId(payment.getPaymentId())
            .walletId(payment.getWallet().getWalletId())
            .paymentMode(payment.getPaymentMode())
            .status(payment.getStatus())
            .build();
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
            .map(payment -> PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .walletId(payment.getWallet().getWalletId())
                .paymentMode(payment.getPaymentMode())
                .status(payment.getStatus())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public PaymentResponse updatePayment(Long id, PaymentRequest request) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getStatus());
        Payment saved = paymentRepository.save(payment);
        log.info("Updated Payment: {}", saved.getPaymentId());
        return PaymentResponse.builder()
            .paymentId(saved.getPaymentId())
            .walletId(saved.getWallet().getWalletId())
            .paymentMode(saved.getPaymentMode())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");
        }
        paymentRepository.deleteById(id);
        log.info("Deleted Payment: {}", id);
    }
}
