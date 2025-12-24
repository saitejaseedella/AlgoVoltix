package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StationOwnerController {
    @Operation(summary = "Create Station Owner", description = "Create a new station owner.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station owner created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<StationOwnerResponse> createStationOwner(@RequestBody StationOwnerRequest request);

    @Operation(summary = "Get Station Owner by ID", description = "Retrieve a station owner by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station owner found"),
        @ApiResponse(responseCode = "404", description = "Station owner not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<StationOwnerResponse> getStationOwnerById(@Parameter(description = "ID of the station owner", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Station Owners", description = "Retrieve all station owners.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of station owners")
    })
    @GetMapping
    ResponseEntity<List<StationOwnerResponse>> getAllStationOwners();

    @Operation(summary = "Update Station Owner", description = "Update an existing station owner.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station owner updated successfully"),
        @ApiResponse(responseCode = "404", description = "Station owner not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<StationOwnerResponse> updateStationOwner(@Parameter(description = "ID of the station owner", required = true) @PathVariable Long id, @RequestBody StationOwnerRequest request);

    @Operation(summary = "Delete Station Owner", description = "Delete a station owner by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Station owner deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Station owner not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteStationOwner(@Parameter(description = "ID of the station owner", required = true) @PathVariable Long id);
}
