package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
