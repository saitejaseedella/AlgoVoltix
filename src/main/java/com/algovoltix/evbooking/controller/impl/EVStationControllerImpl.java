package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.EVStationController;
import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import com.algovoltix.evbooking.service.EVStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EVStationControllerImpl implements EVStationController {
    private final EVStationService evStationService;

    @Override
    public ResponseEntity<EVStationResponse> createEVStation(EVStationRequest request) {
        return ResponseEntity.ok(evStationService.createEVStation(request));
    }

    @Override
    public ResponseEntity<EVStationResponse> getEVStationById(UUID id) {
        return ResponseEntity.ok(evStationService.getEVStationById(id));
    }

    @Override
    public ResponseEntity<List<EVStationResponse>> getAllEVStations() {
        return ResponseEntity.ok(evStationService.getAllEVStations());
    }

    @Override
    public ResponseEntity<EVStationResponse> updateEVStation(UUID id, EVStationRequest request) {
        return ResponseEntity.ok(evStationService.updateEVStation(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteEVStation(UUID id) {
        evStationService.deleteEVStation(id);
        return ResponseEntity.noContent().build();
    }
}
