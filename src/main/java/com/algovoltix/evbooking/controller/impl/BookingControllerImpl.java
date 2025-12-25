package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.BookingController;
import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import com.algovoltix.evbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;

    @Override
    public ResponseEntity<BookingResponse> createBooking(BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
    }

    @Override
    public ResponseEntity<BookingResponse> getBookingById(UUID id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @Override
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Override
    public ResponseEntity<BookingResponse> updateBooking(UUID id, BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.updateBooking(id, bookingRequest));
    }

    @Override
    public ResponseEntity<Void> deleteBooking(UUID id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
