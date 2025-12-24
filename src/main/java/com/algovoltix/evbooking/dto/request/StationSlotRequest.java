package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationSlotRequest {
    private Long stationId;
    private String status;
}

