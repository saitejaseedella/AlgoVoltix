package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.EVStationController;
import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import com.algovoltix.evbooking.service.EVStationService;
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
@RequestMapping("/api/evstations")
@RequiredArgsConstructor
@Tag(name = "EV Station", description = "APIs for managing EV Stations")
public class EVStationControllerImpl implements EVStationController {
    private final EVStationService evStationService;

    @Override
    @Operation(summary = "Create EV Station", description = "Create a new EV charging station.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "EV Station created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<EVStationResponse> createEVStation(@RequestBody EVStationRequest request) {
        return ResponseEntity.ok(evStationService.createEVStation(request));
    }

    @Override
    @Operation(summary = "Get EV Station by ID", description = "Retrieve an EV charging station by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "EV Station found"),
        @ApiResponse(responseCode = "404", description = "EV Station not found")
    })
    public ResponseEntity<EVStationResponse> getEVStationById(@Parameter(description = "ID of the EV Station", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(evStationService.getEVStationById(id));
    }

    @Override
    @Operation(summary = "Get All EV Stations", description = "Retrieve all EV charging stations.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of EV Stations")
    })
    public ResponseEntity<List<EVStationResponse>> getAllEVStations() {
        return ResponseEntity.ok(evStationService.getAllEVStations());
    }

    @Override
    @Operation(summary = "Update EV Station", description = "Update an existing EV charging station.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "EV Station updated successfully"),
        @ApiResponse(responseCode = "404", description = "EV Station not found")
    })
    public ResponseEntity<EVStationResponse> updateEVStation(@PathVariable Long id, @RequestBody EVStationRequest request) {
        return ResponseEntity.ok(evStationService.updateEVStation(id, request));
    }

    @Override
    @Operation(summary = "Delete EV Station", description = "Delete an EV charging station by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "EV Station deleted successfully"),
        @ApiResponse(responseCode = "404", description = "EV Station not found")
    })
    public ResponseEntity<Void> deleteEVStation(@Parameter(description = "ID of the EV Station", required = true) @PathVariable Long id) {
        evStationService.deleteEVStation(id);
        return ResponseEntity.noContent().build();
    }
}
