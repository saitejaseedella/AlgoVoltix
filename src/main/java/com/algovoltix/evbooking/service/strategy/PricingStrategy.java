package com.algovoltix.evbooking.service.strategy;


import com.algovoltix.evbooking.dto.BookingContext;

import java.math.BigDecimal;

public interface PricingStrategy {

  BigDecimal calculatePrice(BookingContext context);
}

