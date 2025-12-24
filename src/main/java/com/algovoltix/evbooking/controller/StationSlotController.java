package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.StationSlotRequest;
import com.algovoltix.evbooking.dto.response.StationSlotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Station Slot", description = "APIs for managing station slots")
public interface StationSlotController {
    @Operation(summary = "Create Station Slot", description = "Create a new station slot.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station slot created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<StationSlotResponse> createStationSlot(@RequestBody StationSlotRequest request);

    @Operation(summary = "Get Station Slot by ID", description = "Retrieve a station slot by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station slot found"),
        @ApiResponse(responseCode = "404", description = "Station slot not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<StationSlotResponse> getStationSlotById(@Parameter(description = "ID of the station slot", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Station Slots", description = "Retrieve all station slots.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of station slots")
    })
    @GetMapping
    ResponseEntity<List<StationSlotResponse>> getAllStationSlots();

    @Operation(summary = "Update Station Slot", description = "Update an existing station slot.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station slot updated successfully"),
        @ApiResponse(responseCode = "404", description = "Station slot not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<StationSlotResponse> updateStationSlot(@Parameter(description = "ID of the station slot", required = true) @PathVariable Long id, @RequestBody StationSlotRequest request);

    @Operation(summary = "Delete Station Slot", description = "Delete a station slot by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Station slot deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Station slot not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStationSlot(@Parameter(description = "ID of the station slot", required = true) @PathVariable Long id);
}
