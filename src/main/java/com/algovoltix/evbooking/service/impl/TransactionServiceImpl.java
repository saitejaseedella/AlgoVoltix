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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        log.info("Attempting to create transaction for walletId={}", request.getWalletId());
        Wallet wallet = walletRepository.findById(request.getWalletId())
            .orElseThrow(() -> {
                log.error("Wallet not found for transaction creation: walletId={}", request.getWalletId());
                return com.algovoltix.evbooking.exception.CommonExceptions.WALLET_NOT_FOUND;
            });
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setType(request.getType());
        transaction.setSource(request.getSource());
        transaction.setAmount(request.getAmount());
        Transaction saved = transactionRepository.save(transaction);
        log.info("Transaction created successfully: transactionId={}", saved.getTransactionId());
        return TransactionResponse.builder()
            .transactionId(saved.getTransactionId())
            .walletId(wallet.getWalletId())
            .type(saved.getType())
            .source(saved.getSource())
            .amount(saved.getAmount())
            .build();
    }

    @Override
    public TransactionResponse getTransactionById(UUID id) {
        log.info("Fetching transaction by id={}", id);
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Transaction not found: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
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
        log.info("Fetching all transactions");
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
    public TransactionResponse updateTransaction(UUID id, TransactionRequest request) {
        log.info("Attempting to update transaction: id={}", id);
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Transaction not found for update: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        transaction.setType(request.getType());
        transaction.setSource(request.getSource());
        transaction.setAmount(request.getAmount());
        Transaction saved = transactionRepository.save(transaction);
        log.info("Transaction updated successfully: id={}", id);
        return TransactionResponse.builder()
            .transactionId(saved.getTransactionId())
            .walletId(saved.getWallet().getWalletId())
            .type(saved.getType())
            .source(saved.getSource())
            .amount(saved.getAmount())
            .build();
    }

    @Override
    public void deleteTransaction(UUID id) {
        log.info("Attempting to delete transaction: id={}", id);
        if (!transactionRepository.existsById(id)) {
            log.error("Transaction not found for delete: id={}", id);
            throw com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
        }
        transactionRepository.deleteById(id);
        log.info("Transaction deleted successfully: id={}", id);
    }
}
