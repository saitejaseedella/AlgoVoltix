package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.StationSlotController;
import com.algovoltix.evbooking.dto.request.StationSlotRequest;
import com.algovoltix.evbooking.dto.response.StationSlotResponse;
import com.algovoltix.evbooking.service.StationSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StationSlotControllerImpl implements StationSlotController {
    private final StationSlotService stationSlotService;

    @Override
    public ResponseEntity<StationSlotResponse> createStationSlot(StationSlotRequest request) {
        return ResponseEntity.ok(stationSlotService.createStationSlot(request));
    }

    @Override
    public ResponseEntity<StationSlotResponse> getStationSlotById(UUID id) {
        return ResponseEntity.ok(stationSlotService.getStationSlotById(id));
    }

    @Override
    public ResponseEntity<List<StationSlotResponse>> getAllStationSlots() {
        return ResponseEntity.ok(stationSlotService.getAllStationSlots());
    }

    @Override
    public ResponseEntity<StationSlotResponse> updateStationSlot(UUID id, StationSlotRequest request) {
        return ResponseEntity.ok(stationSlotService.updateStationSlot(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteStationSlot(UUID id) {
        stationSlotService.deleteStationSlot(id);
        return ResponseEntity.noContent().build();
    }
}
