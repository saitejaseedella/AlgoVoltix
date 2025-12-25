package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Booking {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private UUID bookingId;

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
  private Double duration;

  @CreationTimestamp
  @Column(updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;
}
