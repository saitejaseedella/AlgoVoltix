package com.algovoltix.evbooking.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingContext {

  private Long stationId;
  private Long seatId;
  private Instant startTime;
  private Instant endTime;

}
