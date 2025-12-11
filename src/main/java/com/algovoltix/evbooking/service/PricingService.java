package com.algovoltix.evbooking.service;

import java.math.BigDecimal;
import java.time.Instant;

public interface PricingService {

  public BigDecimal calculatePrice(Long stationId, Long seatId, Instant start, Instant end);

  }
