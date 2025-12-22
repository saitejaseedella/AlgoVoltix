package com.algovoltix.evbooking.service.strategy.impl;


import com.algovoltix.evbooking.dto.BookingContext;
import com.algovoltix.evbooking.service.strategy.PricingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
public class SimplePerHourPricingStrategy implements PricingStrategy {

  @Value("${pricing.base-per-hour:50}")
  private BigDecimal basePerHour;

  @Override
  public BigDecimal calculatePrice(BookingContext context) {
    long minutes = Duration.between(context.getStartTime(), context.getEndTime()).toMinutes();
    if (minutes <= 0) {
//      throw new BusinessException("End time must be after start time");
    }
    BigDecimal hours = BigDecimal.valueOf(minutes)
        .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    return basePerHour.multiply(hours);
  }
}

