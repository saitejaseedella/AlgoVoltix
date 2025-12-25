package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.WalletTransactionRequest;
import com.algovoltix.evbooking.dto.response.WalletTransactionResponse;
import com.algovoltix.evbooking.entity.WalletTransaction;
import com.algovoltix.evbooking.repository.WalletTransactionRepository;
import com.algovoltix.evbooking.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Override
    public WalletTransactionResponse createWalletTransaction(WalletTransactionRequest request) {
        WalletTransaction entity = new WalletTransaction();
        entity.setWallet(null);
        entity.setBooking(null);
        entity.setPayment(null);
        entity.setUserId(request.getWalletId());
        entity.setWalletTransactionType(null);
        entity.setAmount(BigDecimal.valueOf(request.getAmount()));
        entity.setCreatedAt(Instant.now());
        entity.setReference(null);
        entity.setTransactionBy(request.getTransactionBy());
        entity.setType(request.getType());
        entity.setSource(request.getSource());
        WalletTransaction saved = walletTransactionRepository.save(entity);
        return WalletTransactionResponse.builder()
            .transactionId(saved.getId())
            .walletId(saved.getUserId())
            .amount(saved.getAmount().doubleValue())
            .type(saved.getType())
            .source(saved.getSource())
            .bookingId(saved.getBooking() != null ? saved.getBooking().getBookingId() : null)
            .paymentId(saved.getPayment() != null ? saved.getPayment().getPaymentId() : null)
            .transactionBy(saved.getTransactionBy())
            .build();
    }

    @Override
    public WalletTransactionResponse getWalletTransactionById(UUID id) {
        Optional<WalletTransaction> walletTransaction = walletTransactionRepository.findById(id);
        if (walletTransaction.isEmpty()) return null;
        WalletTransaction saved = walletTransaction.get();
        return WalletTransactionResponse.builder()
            .transactionId(saved.getId())
            .walletId(saved.getUserId())
            .amount(saved.getAmount().doubleValue())
            .type(saved.getType())
            .source(saved.getSource())
            .bookingId(saved.getBooking() != null ? saved.getBooking().getBookingId() : null)
            .paymentId(saved.getPayment() != null ? saved.getPayment().getPaymentId() : null)
            .transactionBy(saved.getTransactionBy())
            .build();
    }

    @Override
    public List<WalletTransactionResponse> getAllWalletTransactions() {
        return walletTransactionRepository.findAll().stream().map(saved ->
            WalletTransactionResponse.builder()
                .transactionId(saved.getId())
                .walletId(saved.getUserId())
                .amount(saved.getAmount().doubleValue())
                .type(saved.getType())
                .source(saved.getSource())
                .bookingId(saved.getBooking() != null ? saved.getBooking().getBookingId() : null)
                .paymentId(saved.getPayment() != null ? saved.getPayment().getPaymentId() : null)
                .transactionBy(saved.getTransactionBy())
                .build()
        ).toList();
    }

    @Override
    public WalletTransactionResponse updateWalletTransaction(UUID id, WalletTransactionRequest request) {
        Optional<WalletTransaction> opt = walletTransactionRepository.findById(id);
        if (opt.isEmpty()) return null;
        WalletTransaction entity = opt.get();
        entity.setUserId(request.getWalletId());
        entity.setAmount(BigDecimal.valueOf(request.getAmount()));
        entity.setType(request.getType());
        entity.setSource(request.getSource());
        entity.setTransactionBy(request.getTransactionBy());
        WalletTransaction saved = walletTransactionRepository.save(entity);
        return WalletTransactionResponse.builder()
            .transactionId(saved.getId())
            .walletId(saved.getUserId())
            .amount(saved.getAmount().doubleValue())
            .type(saved.getType())
            .source(saved.getSource())
            .bookingId(saved.getBooking() != null ? saved.getBooking().getBookingId() : null)
            .paymentId(saved.getPayment() != null ? saved.getPayment().getPaymentId() : null)
            .transactionBy(saved.getTransactionBy())
            .build();
    }

    @Override
    public void deleteWalletTransaction(UUID id) {
        walletTransactionRepository.deleteById(id);
    }
}
