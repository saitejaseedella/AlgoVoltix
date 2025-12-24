package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import java.util.List;

public interface StationOwnerService {
    StationOwnerResponse createStationOwner(StationOwnerRequest request);
    StationOwnerResponse getStationOwnerById(Long id);
    List<StationOwnerResponse> getAllStationOwners();
    StationOwnerResponse updateStationOwner(Long id, StationOwnerRequest request);
    void deleteStationOwner(Long id);
}
