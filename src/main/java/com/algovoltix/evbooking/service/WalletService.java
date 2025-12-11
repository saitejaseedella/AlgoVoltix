package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.entity.Wallet;
import java.util.List;

public interface WalletService {
    Wallet createWallet(Wallet wallet);
    Wallet getWalletById(Long id);
    List<Wallet> getAllWallets();
    Wallet updateWallet(Long id, Wallet wallet);
    void deleteWallet(Long id);
}
