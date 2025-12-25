package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import com.algovoltix.evbooking.repository.EVStationRepository;
import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.service.EVStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EVStationServiceImpl implements EVStationService {
    private final EVStationRepository evStationRepository;

    @Override
    public EVStationResponse createEVStation(EVStationRequest request) {
        log.info("Attempting to create EV station");
        EVStation station = new EVStation();
        station.setGeoLocation(request.getGeoLocation());
        station.setStatus(request.getStatus());
        station.setCapacity(request.getCapacity());
        EVStation saved = evStationRepository.save(station);
        log.info("EVStation created successfully: stationId={}", saved.getStationId());
        return EVStationResponse.builder()
            .stationId(saved.getStationId())
            .name(saved.getName())
            .geoLocation(saved.getGeoLocation())
            .status(saved.getStatus())
            .capacity(saved.getCapacity())
            .ownerId(saved.getOwner() != null ? saved.getOwner().getUserId() : null)
            .createdAt(saved.getCreatedAt())
            .updatedAt(saved.getUpdatedAt())
            .build();
    }

    @Override
    public EVStationResponse getEVStationById(UUID id) {
        log.info("Fetching EV station by id={}", id);
        EVStation station = evStationRepository.findById(id)
            .orElseThrow(() -> {
                log.error("EVStation not found: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.EV_STATION_NOT_FOUND;
            });
        return EVStationResponse.builder()
            .stationId(station.getStationId())
            .name(null)
            .geoLocation(station.getGeoLocation())
            .status(station.getStatus())
            .capacity(0)
            .ownerId(null)
            .createdAt(null)
            .updatedAt(null)
            .build();
    }

    @Override
    public List<EVStationResponse> getAllEVStations() {
        log.info("Fetching all EV stations");
        return evStationRepository.findAll().stream()
            .map(station -> EVStationResponse.builder()
                .stationId(station.getStationId())
                .name(null)
                .geoLocation(station.getGeoLocation())
                .status(station.getStatus())
                .capacity(0)
                .ownerId(null)
                .createdAt(null)
                .updatedAt(null)
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public EVStationResponse updateEVStation(UUID id, EVStationRequest request) {
        log.info("Attempting to update EV station: id={}", id);
        EVStation station = evStationRepository.findById(id)
            .orElseThrow(() -> {
                log.error("EVStation not found for update: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.EV_STATION_NOT_FOUND;
            });
        station.setGeoLocation(request.getGeoLocation());
        station.setStatus(request.getStatus());
        station.setCapacity(request.getCapacity());
        EVStation saved = evStationRepository.save(station);
        log.info("EVStation updated successfully: id={}", id);
        return EVStationResponse.builder()
            .stationId(saved.getStationId())
            .name(saved.getName())
            .geoLocation(saved.getGeoLocation())
            .status(saved.getStatus())
            .capacity(saved.getCapacity())
            .ownerId(saved.getOwner() != null ? saved.getOwner().getUserId() : null)
            .createdAt(saved.getCreatedAt())
            .updatedAt(saved.getUpdatedAt())
            .build();
    }

    @Override
    public void deleteEVStation(UUID id) {
        log.info("Attempting to delete EV station: id={}", id);
        if (!evStationRepository.existsById(id)) {
            log.error("EVStation not found for delete: id={}", id);
            throw com.algovoltix.evbooking.exception.CommonExceptions.EV_STATION_NOT_FOUND;
        }
        evStationRepository.deleteById(id);
        log.info("EVStation deleted successfully: id={}", id);
    }
}
