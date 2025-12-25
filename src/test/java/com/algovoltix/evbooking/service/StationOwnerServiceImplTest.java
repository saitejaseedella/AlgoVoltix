package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import com.algovoltix.evbooking.entity.BaseUser;
import com.algovoltix.evbooking.entity.StationOwner;
import com.algovoltix.evbooking.repository.BaseUserRepository;
import com.algovoltix.evbooking.repository.StationOwnerRepository;
import com.algovoltix.evbooking.service.impl.StationOwnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StationOwnerServiceImplTest {
    @Mock
    private StationOwnerRepository stationOwnerRepository;
    @Mock
    private BaseUserRepository baseUserRepository;
    @InjectMocks
    private StationOwnerServiceImpl stationOwnerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStationOwner_success() {
        UUID userId = UUID.randomUUID();
        StationOwnerRequest request = StationOwnerRequest.builder()
                .userId(userId)
                .gstNumber("GST123")
                .build();
        BaseUser user = new BaseUser();
        when(baseUserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(stationOwnerRepository.save(any(StationOwner.class))).thenAnswer(i -> {
            StationOwner o = i.getArgument(0);
            o.setUserId(userId);
            return o;
        });
        StationOwnerResponse response = stationOwnerService.createStationOwner(request);
        assertEquals(userId, response.getUserId());
        verify(stationOwnerRepository, times(1)).save(any(StationOwner.class));
    }

    @Test
    void createStationOwner_userNotFound() {
        UUID userId = UUID.randomUUID();
        StationOwnerRequest request = StationOwnerRequest.builder()
                .userId(userId)
                .gstNumber("GST123")
                .build();
        when(baseUserRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> stationOwnerService.createStationOwner(request));
    }

    @Test
    void getStationOwnerById_success() {
        UUID userId = UUID.randomUUID();
        StationOwner owner = new StationOwner();
        owner.setUserId(userId);
        when(stationOwnerRepository.findById(userId)).thenReturn(Optional.of(owner));
        StationOwnerResponse response = stationOwnerService.getStationOwnerById(userId);
        assertEquals(userId, response.getUserId());
    }

    @Test
    void getStationOwnerById_notFound() {
        UUID userId = UUID.randomUUID();
        when(stationOwnerRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> stationOwnerService.getStationOwnerById(userId));
    }
}

