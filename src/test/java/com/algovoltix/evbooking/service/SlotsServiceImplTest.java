package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.entity.Slots;
import com.algovoltix.evbooking.repository.SeatRepository;
import com.algovoltix.evbooking.service.impl.SlotsServiceImpl;
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

class SlotsServiceImplTest {
    @Mock
    private SeatRepository seatRepository;
    @InjectMocks
    private SlotsServiceImpl slotsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSlotById_notFound() {
        UUID slotId = UUID.randomUUID();
        when(seatRepository.findById(slotId)).thenReturn(Optional.empty());
        assertNull(slotsService.getSlotById(slotId));
    }

    @Test
    void getSlotById_success() {
        UUID slotId = UUID.randomUUID();
        Slots slot = new Slots();
        when(seatRepository.findById(slotId)).thenReturn(java.util.Optional.of(slot));
        assertEquals(slot, slotsService.getSlotById(slotId));
    }
}
