package com.algovoltix.evbooking.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StationSlotResponse {
    private UUID slotId;
    private UUID stationId;
    private String status;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
