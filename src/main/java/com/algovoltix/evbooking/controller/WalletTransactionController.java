package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.WalletTransactionRequest;
import com.algovoltix.evbooking.dto.response.WalletTransactionResponse;
import com.algovoltix.evbooking.entity.WalletTransaction;
import com.algovoltix.evbooking.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallet-transactions")
public interface WalletTransactionController {

    @PostMapping
    ResponseEntity<WalletTransactionResponse> createWalletTransaction(@RequestBody WalletTransactionRequest request);

    @GetMapping("/{id}")
    ResponseEntity<WalletTransactionResponse> getWalletTransactionById(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<List<WalletTransactionResponse>> getAllWalletTransactions();

    @PutMapping("/{id}")
    ResponseEntity<WalletTransactionResponse> updateWalletTransaction(@PathVariable UUID id, @RequestBody WalletTransactionRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWalletTransaction(@PathVariable UUID id);
}
