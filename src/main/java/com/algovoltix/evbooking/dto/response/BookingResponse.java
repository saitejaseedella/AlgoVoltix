package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class BookingResponse {
    private UUID bookingId;
    private UUID userId;
    private UUID slotId;
    private String startTime;
    private String endTime;
    private String bookingStatus;
    private Double totalPrice;
    private Double duration;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
