package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
