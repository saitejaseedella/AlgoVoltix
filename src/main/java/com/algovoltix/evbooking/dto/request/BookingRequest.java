package com.algovoltix.evbooking.dto.request;

import lombok.Data;
import lombok.Builder;



import lombok.Getter;
import lombok.Setter;
@Builder

@Getter
@Setter
public class BookingRequest {

  private String endTime;
  private String startTime;
  private Long slotId;
  private Long userId;
}
