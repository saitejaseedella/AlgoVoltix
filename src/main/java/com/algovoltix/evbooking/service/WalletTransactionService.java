package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.WalletTransactionRequest;
import com.algovoltix.evbooking.dto.response.WalletTransactionResponse;
import java.util.List;
import java.util.UUID;

public interface WalletTransactionService {
    WalletTransactionResponse createWalletTransaction(WalletTransactionRequest request);
    WalletTransactionResponse getWalletTransactionById(UUID id);
    List<WalletTransactionResponse> getAllWalletTransactions();
    WalletTransactionResponse updateWalletTransaction(UUID id, WalletTransactionRequest request);
    void deleteWalletTransaction(UUID id);
}
