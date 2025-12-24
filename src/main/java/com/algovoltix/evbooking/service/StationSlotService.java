package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.StationSlotRequest;
import com.algovoltix.evbooking.dto.response.StationSlotResponse;
import java.util.List;

public interface StationSlotService {

  StationSlotResponse createStationSlot(StationSlotRequest request);

  StationSlotResponse getStationSlotById(Long id);

  List<StationSlotResponse> getAllStationSlots();

  StationSlotResponse updateStationSlot(Long id, StationSlotRequest request);

  void deleteStationSlot(Long id);
}
