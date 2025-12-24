package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Booking", description = "APIs for managing bookings")
public interface BookingController {
    @Operation(summary = "Create Booking", description = "Create a new booking.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest);

    @Operation(summary = "Get Booking by ID", description = "Retrieve a booking by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking found"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<BookingResponse> getBookingById(@Parameter(description = "ID of the booking", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Bookings", description = "Retrieve all bookings.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of bookings")
    })
    @GetMapping
    ResponseEntity<List<BookingResponse>> getAllBookings();

    @Operation(summary = "Update Booking", description = "Update an existing booking.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<BookingResponse> updateBooking(@Parameter(description = "ID of the booking", required = true) @PathVariable Long id, @RequestBody BookingRequest bookingRequest);

    @Operation(summary = "Delete Booking", description = "Delete a booking by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Booking deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBooking(@Parameter(description = "ID of the booking", required = true) @PathVariable Long id);
}
