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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final WalletRepository walletRepository;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        log.info("Attempting to create payment for walletId={}", request.getWalletId());
        if (request.getWalletId() == null) {
            log.error("Payment creation failed: walletId is null");
            throw com.algovoltix.evbooking.exception.CommonExceptions.BAD_REQUEST;
        }
        if (request.getPaymentMode() == null) {
            log.error("Payment creation failed: paymentMode is null");
            throw com.algovoltix.evbooking.exception.CommonExceptions.BAD_REQUEST;
        }
        Wallet wallet = walletRepository.findById(request.getWalletId())
            .orElseThrow(() -> {
                log.error("Wallet not found for payment creation: walletId={}", request.getWalletId());
                return com.algovoltix.evbooking.exception.CommonExceptions.WALLET_NOT_FOUND;
            });
        Payment payment = new Payment();
        payment.setWallet(wallet);
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getStatus());
        Payment saved = paymentRepository.save(payment);
        log.info("Payment created successfully: paymentId={}", saved.getPaymentId());
        return PaymentResponse.builder()
            .paymentId(saved.getPaymentId())
            .walletId(wallet.getWalletId())
            .paymentMode(saved.getPaymentMode())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public PaymentResponse getPaymentById(UUID id) {
        log.info("Fetching payment by id={}", id);
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Payment not found: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        return PaymentResponse.builder()
            .paymentId(payment.getPaymentId())
            .walletId(payment.getWallet().getWalletId())
            .paymentMode(payment.getPaymentMode())
            .status(payment.getStatus())
            .build();
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        log.info("Fetching all payments");
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
    public PaymentResponse updatePayment(UUID id, PaymentRequest request) {
        log.info("Attempting to update payment: id={}", id);
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Payment not found for update: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getStatus());
        Payment saved = paymentRepository.save(payment);
        log.info("Payment updated successfully: id={}", id);
        return PaymentResponse.builder()
            .paymentId(saved.getPaymentId())
            .walletId(saved.getWallet().getWalletId())
            .paymentMode(saved.getPaymentMode())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public void deletePayment(UUID id) {
        log.info("Attempting to delete payment: id={}", id);
        if (!paymentRepository.existsById(id)) {
            log.error("Payment not found for delete: id={}", id);
            throw com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
        }
        paymentRepository.deleteById(id);
        log.info("Payment deleted successfully: id={}", id);
    }
}
