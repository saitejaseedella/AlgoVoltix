package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.BookingController;
import com.algovoltix.evbooking.dto.BookingResponse;
import com.algovoltix.evbooking.dto.CreateBookingRequest;
import com.algovoltix.evbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/booking")
public class BookingControllerImpl implements BookingController {

    private final BookingService bookingService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }
}
