package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.repository.SeatRepository;
import com.algovoltix.evbooking.service.EVStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EVStationServiceImpl implements EVStationService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public EVStation createEVStation(EVStation evStation) {
        return seatRepository.save(evStation);
    }

    @Override
    public EVStation getEVStationById(Long id) {
        Optional<EVStation> evStation = seatRepository.findById(id);
        return evStation.orElse(null);
    }

    @Override
    public List<EVStation> getAllEVStations() {
        return seatRepository.findAll();
    }

    @Override
    public EVStation updateEVStation(Long id, EVStation evStation) {
        if (seatRepository.existsById(id)) {
            evStation.setId(id);
            return seatRepository.save(evStation);
        }
        return null;
    }

    @Override
    public void deleteEVStation(Long id) {
        seatRepository.deleteById(id);
    }
}
