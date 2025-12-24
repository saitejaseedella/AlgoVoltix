package com.algovoltix.evbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class StationOwner {

    @Id
    private Long userId;

    @OneToOne
    @MapsId
    private BaseUser baseUser;

    private String gstNumber;
}
