package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeatRepository extends JpaRepository<Slots, UUID> {
}
