package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Slots, Long> {
}
