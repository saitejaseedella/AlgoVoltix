package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationSlotResponse {
    private Long slotId;
    private Long stationId;
    private String status;
}

