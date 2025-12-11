package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.WalletController;
import com.algovoltix.evbooking.dto.response.WalletResponseDTO;
import com.algovoltix.evbooking.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletControllerImpl implements WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponseDTO> createWallet(@RequestBody WalletRequestDTO walletRequest) {
        return ResponseEntity.ok(walletService.createWallet(walletRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> getWalletById(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @GetMapping
    public ResponseEntity<List<WalletResponseDTO>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> updateWallet(@PathVariable Long id, @RequestBody WalletRequestDTO walletRequest) {
        return ResponseEntity.ok(walletService.updateWallet(id, walletRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }
}
