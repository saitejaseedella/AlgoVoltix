package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StationSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private EVStation evStation;

    @OneToOne(mappedBy = "stationSlot")
    private RateCard rateCard;

    private String status;
}
