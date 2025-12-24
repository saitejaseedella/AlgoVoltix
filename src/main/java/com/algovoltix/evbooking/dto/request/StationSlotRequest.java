package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StationSlotRequest {
    private Long stationId;
    private String status;
}

