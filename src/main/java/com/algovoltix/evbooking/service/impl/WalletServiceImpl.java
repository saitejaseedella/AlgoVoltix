package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.WalletRequest;
import com.algovoltix.evbooking.dto.response.WalletResponse;
import com.algovoltix.evbooking.repository.WalletRepository;
import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.entity.BaseUser;
import com.algovoltix.evbooking.repository.BaseUserRepository;
import com.algovoltix.evbooking.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final BaseUserRepository baseUserRepository;

    @Override
    public WalletResponse createWallet(WalletRequest request) {
        BaseUser user = baseUserRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(request.getBalance());
        Wallet saved = walletRepository.save(wallet);
        log.info("Created Wallet: {}", saved.getWalletId());
        return WalletResponse.builder()
            .walletId(saved.getWalletId())
            .balance(saved.getBalance())
            .build();
    }

    @Override
    public WalletResponse getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
        return WalletResponse.builder()
            .walletId(wallet.getWalletId())
            .balance(wallet.getBalance())
            .build();
    }

    @Override
    public List<WalletResponse> getAllWallets() {
        return walletRepository.findAll().stream()
            .map(wallet -> WalletResponse.builder()
                .walletId(wallet.getWalletId())
                .balance(wallet.getBalance())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public WalletResponse updateWallet(Long id, WalletRequest request) {
        Wallet wallet = walletRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));
        if (request.getBalance() != null) wallet.setBalance(request.getBalance());
        Wallet saved = walletRepository.save(wallet);
        log.info("Updated Wallet: {}", saved.getWalletId());
        return WalletResponse.builder()
            .walletId(saved.getWalletId())
            .balance(saved.getBalance())
            .build();
    }

    @Override
    public void deleteWallet(Long id) {
        if (!walletRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found");
        }
        walletRepository.deleteById(id);
        log.info("Deleted Wallet: {}", id);
    }
}
