package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.entity.WalletTransaction;
import com.algovoltix.evbooking.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet-transactions")
public interface WalletTransactionController {

    @PostMapping
    ResponseEntity<WalletTransaction> createWalletTransaction(@RequestBody WalletTransaction walletTransaction);

    @GetMapping("/{id}")
    ResponseEntity<WalletTransaction> getWalletTransactionById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<WalletTransaction>> getAllWalletTransactions();

    @PutMapping("/{id}")
    ResponseEntity<WalletTransaction> updateWalletTransaction(@PathVariable Long id, @RequestBody WalletTransaction walletTransaction);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWalletTransaction(@PathVariable Long id);
}
