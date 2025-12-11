package com.algovoltix.evbooking.dto.request;

import lombok.Data;
import lombok.Builder;


@Builder
@Data
public class BookingRequest {

  private String endTime;
  private String startTime;
  private Long slotId;
  private Long userId;
}
