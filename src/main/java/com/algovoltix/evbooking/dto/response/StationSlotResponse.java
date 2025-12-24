package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StationSlotResponse {
    private Long slotId;
    private Long stationId;
    private String status;
}

