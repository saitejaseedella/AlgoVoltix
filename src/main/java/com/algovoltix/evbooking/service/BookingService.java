package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.BookingResponse;
import com.algovoltix.evbooking.dto.CreateBookingRequest;

public interface BookingService {

    BookingResponse createBooking(CreateBookingRequest request);
}
