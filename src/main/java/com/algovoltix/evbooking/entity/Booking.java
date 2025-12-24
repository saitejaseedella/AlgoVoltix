package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private StationSlot stationSlot;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String bookingStatus;
    private Double totalPrice;
}
