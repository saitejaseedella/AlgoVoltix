package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Wallet;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    // Basic CRUD methods are provided by JpaRepository
  boolean existsById(UUID id);
  Optional<Wallet> findById(UUID walletId);
}
