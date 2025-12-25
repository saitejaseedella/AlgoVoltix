package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class StationSlotRequest {
    private UUID stationId;
    private String status;
}
