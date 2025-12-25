package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
