package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RateCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateCardId;

    @OneToOne
    @JoinColumn(name = "slot_id", unique = true)
    private StationSlot stationSlot;

    @ManyToOne
    @JoinColumn(name = "algorithm_id")
    private PriceAlgorithm priceAlgorithm;

    private Double price;

    public StationSlot getSlot() {
        return this.stationSlot;
    }
    public void setSlot(StationSlot slot) {
        this.stationSlot = slot;
    }
}
