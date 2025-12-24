package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
