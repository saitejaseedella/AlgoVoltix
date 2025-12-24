package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.StationSlotController;
import com.algovoltix.evbooking.dto.request.StationSlotRequest;
import com.algovoltix.evbooking.dto.response.StationSlotResponse;
import com.algovoltix.evbooking.service.StationSlotService;
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
@RequestMapping("/api/stationslots")
@RequiredArgsConstructor
@Tag(name = "Station Slot", description = "APIs for managing station slots")
public class StationSlotControllerImpl implements StationSlotController {
    private final StationSlotService stationSlotService;

    @PostMapping
    @Override
    @Operation(summary = "Create Station Slot", description = "Create a new station slot.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station slot created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<StationSlotResponse> createStationSlot(@RequestBody StationSlotRequest request) {
        return ResponseEntity.ok(stationSlotService.createStationSlot(request));
    }

    @GetMapping("/{id}")
    @Override
    @Operation(summary = "Get Station Slot by ID", description = "Retrieve a station slot by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station slot found"),
        @ApiResponse(responseCode = "404", description = "Station slot not found")
    })
    public ResponseEntity<StationSlotResponse> getStationSlotById(@Parameter(description = "ID of the station slot", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(stationSlotService.getStationSlotById(id));
    }

    @GetMapping
    @Override
    @Operation(summary = "Get All Station Slots", description = "Retrieve all station slots.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of station slots")
    })
    public ResponseEntity<List<StationSlotResponse>> getAllStationSlots() {
        return ResponseEntity.ok(stationSlotService.getAllStationSlots());
    }

    @PutMapping("/{id}")
    @Override
    @Operation(summary = "Update Station Slot", description = "Update an existing station slot.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Station slot updated successfully"),
        @ApiResponse(responseCode = "404", description = "Station slot not found")
    })
    public ResponseEntity<StationSlotResponse> updateStationSlot(@PathVariable Long id, @RequestBody StationSlotRequest request) {
        return ResponseEntity.ok(stationSlotService.updateStationSlot(id, request));
    }

    @DeleteMapping("/{id}")
    @Override
    @Operation(summary = "Delete Station Slot", description = "Delete a station slot by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Station slot deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Station slot not found")
    })
    public ResponseEntity<Void> deleteStationSlot(@Parameter(description = "ID of the station slot", required = true) @PathVariable Long id) {
        stationSlotService.deleteStationSlot(id);
        return ResponseEntity.noContent().build();
    }
}
