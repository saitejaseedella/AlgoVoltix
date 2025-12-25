package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.StationSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StationSlotRepository extends JpaRepository<StationSlot, UUID> {
}
