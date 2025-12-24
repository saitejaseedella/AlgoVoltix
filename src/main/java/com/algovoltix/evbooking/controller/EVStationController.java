package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "EV Station", description = "APIs for managing EV Stations")
public interface EVStationController {
    @Operation(summary = "Create EV Station", description = "Create a new EV charging station.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "EV Station created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<EVStationResponse> createEVStation(@RequestBody EVStationRequest request);

    @Operation(summary = "Get EV Station by ID", description = "Retrieve an EV charging station by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "EV Station found"),
        @ApiResponse(responseCode = "404", description = "EV Station not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<EVStationResponse> getEVStationById(@Parameter(description = "ID of the EV Station", required = true) @PathVariable Long id);

    @Operation(summary = "Get All EV Stations", description = "Retrieve all EV charging stations.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of EV Stations")
    })
    @GetMapping
    ResponseEntity<List<EVStationResponse>> getAllEVStations();

    @Operation(summary = "Update EV Station", description = "Update an existing EV charging station.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "EV Station updated successfully"),
        @ApiResponse(responseCode = "404", description = "EV Station not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<EVStationResponse> updateEVStation(@Parameter(description = "ID of the EV Station", required = true) @PathVariable Long id, @RequestBody EVStationRequest request);

    @Operation(summary = "Delete EV Station", description = "Delete an EV charging station by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "EV Station deleted successfully"),
        @ApiResponse(responseCode = "404", description = "EV Station not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEVStation(@Parameter(description = "ID of the EV Station", required = true) @PathVariable Long id);
}
