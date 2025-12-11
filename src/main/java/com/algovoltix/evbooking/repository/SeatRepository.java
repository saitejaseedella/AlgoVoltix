package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<EVStation, Long> {
    // Basic CRUD methods are provided by JpaRepository
}
