package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.BookingController;
import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import com.algovoltix.evbooking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking", description = "APIs for managing bookings")
public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;

    @Override
    @Operation(summary = "Create Booking", description = "Create a new booking.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
    }

    @Override
    @Operation(summary = "Get Booking by ID", description = "Retrieve a booking by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking found"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<BookingResponse> getBookingById(@Parameter(description = "ID of the booking", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @Override
    @Operation(summary = "Get All Bookings", description = "Retrieve all bookings.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of bookings")
    })
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Override
    @Operation(summary = "Update Booking", description = "Update an existing booking.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.updateBooking(id, bookingRequest));
    }

    @Override
    @Operation(summary = "Delete Booking", description = "Delete a booking by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Booking deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    public ResponseEntity<Void> deleteBooking(@Parameter(description = "ID of the booking", required = true) @PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
