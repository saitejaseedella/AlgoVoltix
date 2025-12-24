package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.TransactionRequest;
import com.algovoltix.evbooking.dto.response.TransactionResponse;
import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest request);
    TransactionResponse getTransactionById(Long id);
    List<TransactionResponse> getAllTransactions();
    TransactionResponse updateTransaction(Long id, TransactionRequest request);
    void deleteTransaction(Long id);
}
