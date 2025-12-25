package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class RateCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rateCardId;

    @OneToOne
    @JoinColumn(name = "slot_id", unique = true)
    private StationSlot stationSlot;

    @ManyToOne
    @JoinColumn(name = "algorithm_id")
    private PriceAlgorithm priceAlgorithm;

    private Double price;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public StationSlot getSlot() {
        return this.stationSlot;
    }
    public void setSlot(StationSlot slot) {
        this.stationSlot = slot;
    }
}
