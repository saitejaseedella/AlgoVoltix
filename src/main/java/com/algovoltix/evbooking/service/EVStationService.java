package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import java.util.List;

public interface EVStationService {
    EVStationResponse createEVStation(EVStationRequest request);
    EVStationResponse getEVStationById(Long id);
    List<EVStationResponse> getAllEVStations();
    EVStationResponse updateEVStation(Long id, EVStationRequest request);
    void deleteEVStation(Long id);
}
