package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.TransactionRequest;
import com.algovoltix.evbooking.dto.response.TransactionResponse;
import com.algovoltix.evbooking.entity.Transaction;
import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.repository.TransactionRepository;
import com.algovoltix.evbooking.repository.WalletRepository;
import com.algovoltix.evbooking.service.TransactionService;
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
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setType(request.getType());
        transaction.setSource(request.getSource());
        transaction.setAmount(request.getAmount());
        Transaction saved = transactionRepository.save(transaction);
        log.info("Created Transaction: {}", saved.getTransactionId());
        return TransactionResponse.builder()
            .transactionId(saved.getTransactionId())
            .walletId(wallet.getWalletId())
            .type(saved.getType())
            .source(saved.getSource())
            .amount(saved.getAmount())
            .build();
    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
        return TransactionResponse.builder()
            .transactionId(transaction.getTransactionId())
            .walletId(transaction.getWallet().getWalletId())
            .type(transaction.getType())
            .source(transaction.getSource())
            .amount(transaction.getAmount())
            .build();
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream()
            .map(transaction -> TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .walletId(transaction.getWallet().getWalletId())
                .type(transaction.getType())
                .source(transaction.getSource())
                .amount(transaction.getAmount())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public TransactionResponse updateTransaction(Long id, TransactionRequest request) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
        transaction.setType(request.getType());
        transaction.setSource(request.getSource());
        transaction.setAmount(request.getAmount());
        Transaction saved = transactionRepository.save(transaction);
        log.info("Updated Transaction: {}", saved.getTransactionId());
        return TransactionResponse.builder()
            .transactionId(saved.getTransactionId())
            .walletId(saved.getWallet().getWalletId())
            .type(saved.getType())
            .source(saved.getSource())
            .amount(saved.getAmount())
            .build();
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
        }
        transactionRepository.deleteById(id);
        log.info("Deleted Transaction: {}", id);
    }
}

