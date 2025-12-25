package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.TransactionController;
import com.algovoltix.evbooking.dto.request.TransactionRequest;
import com.algovoltix.evbooking.dto.response.TransactionResponse;
import com.algovoltix.evbooking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {
    private final TransactionService transactionService;

    @Override
    public ResponseEntity<TransactionResponse> createTransaction(TransactionRequest request) {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @Override
    public ResponseEntity<TransactionResponse> getTransactionById(UUID id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @Override
    public ResponseEntity<TransactionResponse> updateTransaction(UUID id, TransactionRequest request) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteTransaction(UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
