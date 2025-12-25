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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.algovoltix.evbooking.exception.CommonExceptions.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationSlotServiceImpl implements StationSlotService {
    private final StationSlotRepository stationSlotRepository;
    private final EVStationRepository evStationRepository;

    @Override
    public StationSlotResponse createStationSlot(StationSlotRequest request) {
        log.info("Attempting to create station slot for stationId={}", request.getStationId());
        EVStation station = evStationRepository.findById(request.getStationId())
            .orElseThrow(() -> {
                log.error("EVStation not found for slot creation: stationId={}", request.getStationId());
                return com.algovoltix.evbooking.exception.CommonExceptions.EV_STATION_NOT_FOUND;
            });
        StationSlot slot = new StationSlot();
        slot.setEvStation(station);
        slot.setStatus(request.getStatus());
        StationSlot saved = stationSlotRepository.save(slot);
        log.info("StationSlot created successfully: slotId={}", saved.getSlotId());
        return StationSlotResponse.builder()
            .slotId(saved.getSlotId())
            .stationId(station.getStationId())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public StationSlotResponse getStationSlotById(UUID id) {
        log.info("Fetching station slot by id={}", id);
        StationSlot slot = stationSlotRepository.findById(id)
            .orElseThrow(() -> {
                log.error("StationSlot not found: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        return StationSlotResponse.builder()
            .slotId(slot.getSlotId())
            .stationId(slot.getEvStation().getStationId())
            .status(slot.getStatus())
            .build();
    }

    @Override
    public List<StationSlotResponse> getAllStationSlots() {
        log.info("Fetching all station slots");
        return stationSlotRepository.findAll().stream()
            .map(slot -> StationSlotResponse.builder()
                .slotId(slot.getSlotId())
                .stationId(slot.getEvStation().getStationId())
                .status(slot.getStatus())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public StationSlotResponse updateStationSlot(UUID id, StationSlotRequest request) {
        log.info("Attempting to update station slot: id={}", id);
        StationSlot slot = stationSlotRepository.findById(id)
            .orElseThrow(() -> {
                log.error("StationSlot not found for update: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        slot.setStatus(request.getStatus());
        StationSlot saved = stationSlotRepository.save(slot);
        log.info("StationSlot updated successfully: id={}", id);
        return StationSlotResponse.builder()
            .slotId(saved.getSlotId())
            .stationId(saved.getEvStation().getStationId())
            .status(saved.getStatus())
            .build();
    }

    @Override
    public void deleteStationSlot(UUID id) {
        log.info("Attempting to delete station slot: id={}", id);
        if (!stationSlotRepository.existsById(id)) {
            log.error("StationSlot not found for delete: id={}", id);
            throw com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
        }
        stationSlotRepository.deleteById(id);
        log.info("StationSlot deleted successfully: id={}", id);
    }
}
