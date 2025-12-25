package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.WalletController;
import com.algovoltix.evbooking.dto.request.WalletRequest;
import com.algovoltix.evbooking.dto.response.WalletResponse;
import com.algovoltix.evbooking.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class WalletControllerImpl implements WalletController {
    private final WalletService walletService;

    @Override
    public ResponseEntity<WalletResponse> createWallet(WalletRequest request) {
        return ResponseEntity.ok(walletService.createWallet(request));
    }

    @Override
    public ResponseEntity<WalletResponse> getWalletById(UUID id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @Override
    public ResponseEntity<List<WalletResponse>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @Override
    public ResponseEntity<WalletResponse> updateWallet(UUID id, WalletRequest request) {
        return ResponseEntity.ok(walletService.updateWallet(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteWallet(UUID id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
}
