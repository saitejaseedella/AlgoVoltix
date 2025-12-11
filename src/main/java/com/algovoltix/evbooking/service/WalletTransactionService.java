package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.entity.WalletTransaction;
import java.util.List;

public interface WalletTransactionService {
    WalletTransaction createWalletTransaction(WalletTransaction walletTransaction);
    WalletTransaction getWalletTransactionById(Long id);
    List<WalletTransaction> getAllWalletTransactions();
    WalletTransaction updateWalletTransaction(Long id, WalletTransaction walletTransaction);
    void deleteWalletTransaction(Long id);
}
