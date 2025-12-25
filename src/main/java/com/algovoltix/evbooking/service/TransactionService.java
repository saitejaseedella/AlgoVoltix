package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.TransactionRequest;
import com.algovoltix.evbooking.dto.response.TransactionResponse;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request);
    TransactionResponse getTransactionById(UUID id);
    List<TransactionResponse> getAllTransactions();
    TransactionResponse updateTransaction(UUID id, TransactionRequest request);
    void deleteTransaction(UUID id);
}
