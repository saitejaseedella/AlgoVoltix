package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.WalletController;
import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.service.WalletService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletControllerImpl implements WalletController {

    @Autowired
    private WalletService walletService;


  @Override
  public ResponseEntity<Wallet> createWallet(Wallet wallet) {
    return null;
  }

  @Override
  public ResponseEntity<Wallet> getWalletById(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Wallet>> getAllWallets() {
    return null;
  }

  @Override
  public ResponseEntity<Wallet> updateWallet(UUID id, Wallet wallet) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteWallet(UUID id) {
    return null;
  }
}
