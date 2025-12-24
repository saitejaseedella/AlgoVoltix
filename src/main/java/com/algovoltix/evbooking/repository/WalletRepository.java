package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
