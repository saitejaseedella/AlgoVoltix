package com.algovoltix.evbooking.dto.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EVStationRequest {
    private String name;
    private String geoLocation;
    private String status;
    private Integer capacity;
    private UUID ownerId;
}
