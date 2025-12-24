package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
