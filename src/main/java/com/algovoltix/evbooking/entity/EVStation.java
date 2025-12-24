package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EVStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private StationOwner owner;

    private String geoLocation;
    private String status;
}
