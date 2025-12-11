package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.entity.Slots;
import java.util.List;

public interface SlotsService {
    Slots createSlot(Slots slot);
    Slots getSlotById(Long id);
    List<Slots> getAllSlots();
    Slots updateSlot(Long id, Slots slot);
    void deleteSlot(Long id);
}
