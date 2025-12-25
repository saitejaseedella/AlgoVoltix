package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class StationSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID slotId;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private EVStation evStation;

    @OneToOne(mappedBy = "stationSlot")
    private RateCard rateCard;

    private String status;
}
