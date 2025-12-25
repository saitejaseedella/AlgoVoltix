package com.algovoltix.evbooking.dto.request;

import lombok.Data;
import lombok.Builder;



import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder

@Getter
@Setter
public class BookingRequest {

  private UUID userId;
  private UUID slotId;
  private String startTime;
  private String endTime;
  private String bookingStatus;
  private Double totalPrice;
  private Double duration;
}
