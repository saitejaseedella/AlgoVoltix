package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import java.util.List;
import java.util.UUID;

public interface StationOwnerService {
    StationOwnerResponse createStationOwner(StationOwnerRequest request);
    StationOwnerResponse getStationOwnerById(UUID id);
    List<StationOwnerResponse> getAllStationOwners();
    StationOwnerResponse updateStationOwner(UUID id, StationOwnerRequest request);
    void deleteStationOwner(UUID id);
}
