package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
