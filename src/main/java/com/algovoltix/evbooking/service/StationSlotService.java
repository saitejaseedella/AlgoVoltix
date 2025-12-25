package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.StationSlotRequest;
import com.algovoltix.evbooking.dto.response.StationSlotResponse;
import java.util.List;
import java.util.UUID;

public interface StationSlotService {

  StationSlotResponse createStationSlot(StationSlotRequest request);

  StationSlotResponse getStationSlotById(UUID id);

  List<StationSlotResponse> getAllStationSlots();

  StationSlotResponse updateStationSlot(UUID id, StationSlotRequest request);

  void deleteStationSlot(UUID id);
}
