package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.StationOwnerController;
import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import com.algovoltix.evbooking.service.StationOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station-owners")
@RequiredArgsConstructor
public class StationOwnerControllerImpl implements StationOwnerController {
    private final StationOwnerService stationOwnerService;

    @Override
    @Operation(summary = "Create Station Owner", description = "Create a new station owner.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station owner created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<StationOwnerResponse> createStationOwner(@RequestBody StationOwnerRequest request) {
        return ResponseEntity.ok(stationOwnerService.createStationOwner(request));
    }

    @Override
    @Operation(summary = "Get Station Owner by ID", description = "Retrieve a station owner by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station owner found"),
        @ApiResponse(responseCode = "404", description = "Station owner not found")
    })
    public ResponseEntity<StationOwnerResponse> getStationOwnerById(@Parameter(description = "ID of the station owner", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(stationOwnerService.getStationOwnerById(id));
    }

    @Override
    @Operation(summary = "Get All Station Owners", description = "Retrieve all station owners.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of station owners")
    })
    public ResponseEntity<List<StationOwnerResponse>> getAllStationOwners() {
        return ResponseEntity.ok(stationOwnerService.getAllStationOwners());
    }

    @Override
    @Operation(summary = "Update Station Owner", description = "Update an existing station owner.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station owner updated successfully"),
        @ApiResponse(responseCode = "404", description = "Station owner not found")
    })
    public ResponseEntity<StationOwnerResponse> updateStationOwner(@PathVariable Long id, @RequestBody StationOwnerRequest request) {
        return ResponseEntity.ok(stationOwnerService.updateStationOwner(id, request));
    }

    @Override
    @Operation(summary = "Delete Station Owner", description = "Delete a station owner by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Station owner deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Station owner not found")
    })
    public ResponseEntity<Void> deleteStationOwner(@Parameter(description = "ID of the station owner", required = true) @PathVariable Long id) {
        stationOwnerService.deleteStationOwner(id);
        return ResponseEntity.noContent().build();
    }
}
