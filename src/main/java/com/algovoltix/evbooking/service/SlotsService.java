package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.entity.Slots;
import java.util.List;
import java.util.UUID;

public interface SlotsService {
    Slots createSlot(Slots slot);
    Slots getSlotById(UUID id);
    List<Slots> getAllSlots();
    Slots updateSlot(UUID id, Slots slot);
    void deleteSlot(UUID id);
}
