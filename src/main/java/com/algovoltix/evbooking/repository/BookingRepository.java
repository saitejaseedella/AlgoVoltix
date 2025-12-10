package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.Booking;
import com.algovoltix.evbooking.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBySeatAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Slots seat,
            Instant endTime,
            Instant startTime
    );
}
