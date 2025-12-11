package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.WalletTransactionController;
import com.algovoltix.evbooking.entity.WalletTransaction;
import com.algovoltix.evbooking.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet-transactions")
public class WalletTransactionControllerImpl implements WalletTransactionController {

    @Autowired
    private WalletTransactionService walletTransactionService;

    @PostMapping
    public ResponseEntity<WalletTransaction> createWalletTransaction(@RequestBody WalletTransaction walletTransaction) {
        return ResponseEntity.ok(walletTransactionService.createWalletTransaction(walletTransaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletTransaction> getWalletTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(walletTransactionService.getWalletTransactionById(id));
    }

    @GetMapping
    public ResponseEntity<List<WalletTransaction>> getAllWalletTransactions() {
        return ResponseEntity.ok(walletTransactionService.getAllWalletTransactions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletTransaction> updateWalletTransaction(@PathVariable Long id, @RequestBody WalletTransaction walletTransaction) {
        return ResponseEntity.ok(walletTransactionService.updateWalletTransaction(id, walletTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletTransaction(@PathVariable Long id) {
        walletTransactionService.deleteWalletTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
