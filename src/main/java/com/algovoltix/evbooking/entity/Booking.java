package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

      @ManyToOne(fetch = FetchType.LAZY, optional = false)
      @JoinColumn(name = "customer_id", referencedColumnName = "user_id", nullable = false)
      private Customer customer;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private StationSlot stationSlot;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String bookingStatus;
    private Double totalPrice;
}
