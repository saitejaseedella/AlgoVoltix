package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.entity.Slots;
import com.algovoltix.evbooking.repository.SeatRepository;
import com.algovoltix.evbooking.service.SlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SlotsServiceImpl implements SlotsService {

    @Autowired
    private SeatRepository seatRepository;

  @Override
  public Slots createSlot(Slots slot) {
    return null;
  }

  @Override
  public Slots getSlotById(UUID id) {
    return seatRepository.findById(id).orElse(null);
  }

  @Override
  public List<Slots> getAllSlots() {
    return List.of();
  }

  @Override
  public Slots updateSlot(UUID id, Slots slot) {
    return null;
  }

  @Override
  public void deleteSlot(UUID id) {

  }
}
