package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface IBookingController {

    @PostMapping
    ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest);

    @GetMapping("/{id}")
    ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<BookingResponse>> getAllBookings();

    @PutMapping("/{id}")
    ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBooking(@PathVariable Long id);
}
