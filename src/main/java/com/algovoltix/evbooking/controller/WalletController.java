package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public interface WalletController {

    @PostMapping
    ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet);

    @GetMapping("/{id}")
    ResponseEntity<Wallet> getWalletById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<Wallet>> getAllWallets();

    @PutMapping("/{id}")
    ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteWallet(@PathVariable Long id);
}
