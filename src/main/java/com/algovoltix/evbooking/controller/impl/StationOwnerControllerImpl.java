package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.StationOwnerController;
import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import com.algovoltix.evbooking.service.StationOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StationOwnerControllerImpl implements StationOwnerController {
    private final StationOwnerService stationOwnerService;

    @Override
    public ResponseEntity<StationOwnerResponse> createStationOwner(StationOwnerRequest request) {
        return ResponseEntity.ok(stationOwnerService.createStationOwner(request));
    }

    @Override
    public ResponseEntity<StationOwnerResponse> getStationOwnerById(UUID id) {
        return ResponseEntity.ok(stationOwnerService.getStationOwnerById(id));
    }

    @Override
    public ResponseEntity<List<StationOwnerResponse>> getAllStationOwners() {
        return ResponseEntity.ok(stationOwnerService.getAllStationOwners());
    }

    @Override
    public ResponseEntity<StationOwnerResponse> updateStationOwner(UUID id, StationOwnerRequest request) {
        return ResponseEntity.ok(stationOwnerService.updateStationOwner(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteStationOwner(UUID id) {
        stationOwnerService.deleteStationOwner(id);
        return ResponseEntity.noContent().build();
    }
}
