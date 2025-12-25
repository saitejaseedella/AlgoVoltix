package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import java.util.List;
import java.util.UUID;

public interface EVStationService {
    EVStationResponse createEVStation(EVStationRequest request);
    EVStationResponse getEVStationById(UUID id);
    List<EVStationResponse> getAllEVStations();
    EVStationResponse updateEVStation(UUID id, EVStationRequest request);
    void deleteEVStation(UUID id);
}
