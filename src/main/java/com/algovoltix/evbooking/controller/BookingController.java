package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.BookingResponse;
import com.algovoltix.evbooking.dto.CreateBookingRequest;
import org.springframework.http.ResponseEntity;

public interface BookingController {

    ResponseEntity<BookingResponse> createBooking(CreateBookingRequest request);
}
