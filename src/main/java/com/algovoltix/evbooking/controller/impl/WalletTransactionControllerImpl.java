package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.WalletTransactionController;
import com.algovoltix.evbooking.dto.request.WalletTransactionRequest;
import com.algovoltix.evbooking.dto.response.WalletTransactionResponse;
import com.algovoltix.evbooking.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class WalletTransactionControllerImpl implements WalletTransactionController {
    private final WalletTransactionService walletTransactionService;

    @Override
    public ResponseEntity<WalletTransactionResponse> createWalletTransaction(@RequestBody WalletTransactionRequest request) {
        return ResponseEntity.ok(walletTransactionService.createWalletTransaction(request));
    }

    @Override
    public ResponseEntity<WalletTransactionResponse> getWalletTransactionById(@PathVariable UUID id) {
        return ResponseEntity.ok(walletTransactionService.getWalletTransactionById(id));
    }

    @Override
    public ResponseEntity<List<WalletTransactionResponse>> getAllWalletTransactions() {
        return ResponseEntity.ok(walletTransactionService.getAllWalletTransactions());
    }

    @Override
    public ResponseEntity<WalletTransactionResponse> updateWalletTransaction(@PathVariable UUID id, @RequestBody WalletTransactionRequest request) {
        return ResponseEntity.ok(walletTransactionService.updateWalletTransaction(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteWalletTransaction(@PathVariable UUID id) {
        walletTransactionService.deleteWalletTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
