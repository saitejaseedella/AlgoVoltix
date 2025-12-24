package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import com.algovoltix.evbooking.repository.EVStationRepository;
import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.service.EVStationService;
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
public class EVStationServiceImpl implements EVStationService {
    private final EVStationRepository evStationRepository;

    @Override
    public EVStationResponse createEVStation(EVStationRequest request) {
        EVStation station = new EVStation();
        station.setGeoLocation(request.getLocation());
        station.setStatus("ACTIVE");
        EVStation saved = evStationRepository.save(station);
        log.info("Created EVStation: {}", saved.getStationId());
        return EVStationResponse.builder()
            .id(saved.getStationId())
            .name(request.getName())
            .location(request.getLocation())
            .capacity(request.getCapacity())
            .build();
    }

    @Override
    public EVStationResponse getEVStationById(Long id) {
        EVStation station = evStationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EVStation not found"));
        return EVStationResponse.builder()
            .id(station.getStationId())
            .name(null)
            .location(station.getGeoLocation())
            .capacity(0)
            .build();
    }

    @Override
    public List<EVStationResponse> getAllEVStations() {
        return evStationRepository.findAll().stream()
            .map(station -> EVStationResponse.builder()
                .id(station.getStationId())
                .name(null)
                .location(station.getGeoLocation())
                .capacity(0)
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public EVStationResponse updateEVStation(Long id, EVStationRequest request) {
        EVStation station = evStationRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EVStation not found"));
        station.setGeoLocation(request.getLocation());
        station.setStatus("ACTIVE");
        EVStation saved = evStationRepository.save(station);
        log.info("Updated EVStation: {}", saved.getStationId());
        return EVStationResponse.builder()
            .id(saved.getStationId())
            .name(request.getName())
            .location(request.getLocation())
            .capacity(request.getCapacity())
            .build();
    }

    @Override
    public void deleteEVStation(Long id) {
        if (!evStationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EVStation not found");
        }
        evStationRepository.deleteById(id);
        log.info("Deleted EVStation: {}", id);
    }
}
