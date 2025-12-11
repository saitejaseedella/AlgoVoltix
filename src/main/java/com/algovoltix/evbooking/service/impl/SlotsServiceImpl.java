package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.entity.Slots;
import com.algovoltix.evbooking.repository.SeatRepository;
import com.algovoltix.evbooking.service.SlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotsServiceImpl implements SlotsService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Slots createSlot(Slots slot) {
        return seatRepository.save(slot);
    }

    @Override
    public Slots getSlotById(Long id) {
        Optional<Slots> slot = seatRepository.findById(id);
        return slot.orElse(null);
    }

    @Override
    public List<Slots> getAllSlots() {
        return seatRepository.findAll();
    }

    @Override
    public Slots updateSlot(Long id, Slots slot) {
        if (seatRepository.existsById(id)) {
            slot.setId(id);
            return seatRepository.save(slot);
        }
        return null;
    }

    @Override
    public void deleteSlot(Long id) {
        seatRepository.deleteById(id);
    }
}
