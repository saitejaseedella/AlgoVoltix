package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.EVStationRequest;
import com.algovoltix.evbooking.dto.response.EVStationResponse;
import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.repository.EVStationRepository;
import com.algovoltix.evbooking.service.impl.EVStationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EVStationServiceImplTest {
    @Mock
    private EVStationRepository evStationRepository;
    @InjectMocks
    private EVStationServiceImpl evStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEVStation_success() {
        EVStationRequest request = EVStationRequest.builder()
                .geoLocation("loc")
                .status("ACTIVE")
                .capacity(10)
                .build();
        EVStation station = new EVStation();
        station.setStationId(UUID.randomUUID());
        when(evStationRepository.save(any(EVStation.class))).thenReturn(station);
        EVStationResponse response = evStationService.createEVStation(request);
        assertNotNull(response.getStationId());
        verify(evStationRepository, times(1)).save(any(EVStation.class));
    }

    @Test
    void getEVStationById_success() {
        UUID id = UUID.randomUUID();
        EVStation station = new EVStation();
        station.setStationId(id);
        when(evStationRepository.findById(id)).thenReturn(Optional.of(station));
        EVStationResponse response = evStationService.getEVStationById(id);
        assertEquals(id, response.getStationId());
    }

    @Test
    void getEVStationById_notFound() {
        UUID id = UUID.randomUUID();
        when(evStationRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(com.algovoltix.evbooking.exception.CommonExceptions.class, () -> evStationService.getEVStationById(id));
        assertEquals(com.algovoltix.evbooking.exception.CommonExceptions.EV_STATION_NOT_FOUND.getMessage(), exception.getMessage());
    }
}
