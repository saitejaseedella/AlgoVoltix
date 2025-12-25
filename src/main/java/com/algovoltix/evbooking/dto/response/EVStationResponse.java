package com.algovoltix.evbooking.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EVStationResponse {
    private UUID stationId;
    private String name;
    private String geoLocation;
    private String status;
    private Integer capacity;
    private UUID ownerId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
