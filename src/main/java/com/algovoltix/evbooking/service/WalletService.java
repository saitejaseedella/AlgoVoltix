package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.entity.Wallet;
import java.util.List;
import java.util.UUID;

public interface WalletService {
    Wallet createWallet(Wallet wallet);
    Wallet getWalletById(UUID id);
    List<Wallet> getAllWallets();
    Wallet updateWallet(UUID id, Wallet wallet);
    void deleteWallet(UUID id);
}
