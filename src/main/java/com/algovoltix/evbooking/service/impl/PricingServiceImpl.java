package com.algovoltix.evbooking.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl {

  private static final BigDecimal BASE_PER_HOUR = new BigDecimal("50.0");

  public BigDecimal calculatePrice(Long stationId, Long seatId, Instant start, Instant end) {
    long minutes = Duration.between(start, end).toMinutes();
    if (minutes <= 0) {
      throw new IllegalArgumentException("End time must be after start time");
    }
    BigDecimal hours = BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60), 2, BigDecimal.ROUND_HALF_UP);
    return BASE_PER_HOUR.multiply(hours);
  }
}