package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.entity.WalletTransaction;
import com.algovoltix.evbooking.repository.WalletTransactionRepository;
import com.algovoltix.evbooking.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Override
    public WalletTransaction createWalletTransaction(WalletTransaction walletTransaction) {
        return walletTransactionRepository.save(walletTransaction);
    }

    @Override
    public WalletTransaction getWalletTransactionById(Long id) {
        Optional<WalletTransaction> walletTransaction = walletTransactionRepository.findById(id);
        return walletTransaction.orElse(null);
    }

    @Override
    public List<WalletTransaction> getAllWalletTransactions() {
        return walletTransactionRepository.findAll();
    }

    @Override
    public WalletTransaction updateWalletTransaction(Long id, WalletTransaction walletTransaction) {
        if (walletTransactionRepository.existsById(id)) {
            walletTransaction.setId(id);
            return walletTransactionRepository.save(walletTransaction);
        }
        return null;
    }

    @Override
    public void deleteWalletTransaction(Long id) {
        walletTransactionRepository.deleteById(id);
    }
}
