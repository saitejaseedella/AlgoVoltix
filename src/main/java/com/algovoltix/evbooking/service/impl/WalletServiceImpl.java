package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.repository.WalletRepository;
import com.algovoltix.evbooking.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElse(null);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet updateWallet(Long id, Wallet wallet) {
        if (walletRepository.existsById(id)) {
            wallet.setId(id);
            return walletRepository.save(wallet);
        }
        return null;
    }

    @Override
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
