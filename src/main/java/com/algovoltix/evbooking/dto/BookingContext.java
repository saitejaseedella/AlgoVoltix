package com.algovoltix.evbooking.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingContext {

  private Long stationId;
  private Long seatId;
  private Instant startTime;
  private Instant endTime;

}
