package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class EVStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID stationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private StationOwner owner;

    private String name;
    private String geoLocation;
    private String status;
    private Integer capacity;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
