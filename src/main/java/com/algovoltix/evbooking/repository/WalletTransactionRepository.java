package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}
