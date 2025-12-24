package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.StationSlotRequest;
import com.algovoltix.evbooking.dto.response.StationSlotResponse;
import com.algovoltix.evbooking.entity.StationSlot;
import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.repository.StationSlotRepository;
import com.algovoltix.evbooking.repository.EVStationRepository;
import com.algovoltix.evbooking.service.StationSlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationSlotServiceImpl implements StationSlotService {
    private final StationSlotRepository stationSlotRepository;
    private final EVStationRepository evStationRepository;

    @Override
    public StationSlotResponse createStationSlot(StationSlotRequest request) {
        EVStation station = evStationRepository.findById(request.getStationId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EVStation not found"));
        StationSlot slot = new StationSlot();
        slot.setEvStation(station);
        slot.setStatus(request.getStatus());
        StationSlot saved = stationSlotRepository.save(slot);
        log.info("Created StationSlot: {}", saved.getSlotId());
        return StationSlotResponse.builder()
            .slotId(saved.getSlotId())
            .stationId(station.getStationId())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public StationSlotResponse getStationSlotById(Long id) {
        StationSlot slot = stationSlotRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StationSlot not found"));
        return StationSlotResponse.builder()
            .slotId(slot.getSlotId())
            .stationId(slot.getEvStation().getStationId())
            .status(slot.getStatus())
            .build();
    }

    @Override
    public List<StationSlotResponse> getAllStationSlots() {
        return stationSlotRepository.findAll().stream()
            .map(slot -> StationSlotResponse.builder()
                .slotId(slot.getSlotId())
                .stationId(slot.getEvStation().getStationId())
                .status(slot.getStatus())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public StationSlotResponse updateStationSlot(Long id, StationSlotRequest request) {
        StationSlot slot = stationSlotRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StationSlot not found"));
        slot.setStatus(request.getStatus());
        StationSlot saved = stationSlotRepository.save(slot);
        log.info("Updated StationSlot: {}", saved.getSlotId());
        return StationSlotResponse.builder()
            .slotId(saved.getSlotId())
            .stationId(saved.getEvStation().getStationId())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public void deleteStationSlot(Long id) {
        if (!stationSlotRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "StationSlot not found");
        }
        stationSlotRepository.deleteById(id);
        log.info("Deleted StationSlot: {}", id);
    }
}
// The interface and implementation are correct. The error may be due to an old interface definition or a mismatch in the build. Let's ensure the interface and implementation match exactly.
