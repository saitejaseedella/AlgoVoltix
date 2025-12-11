package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.EVStationController;
import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.service.EVStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evstations")
public class EVStationControllerImpl implements EVStationController {

    @Autowired
    private EVStationService evStationService;

    @PostMapping
    public ResponseEntity<EVStation> createEVStation(@RequestBody EVStation evStation) {
        return ResponseEntity.ok(evStationService.createEVStation(evStation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EVStation> getEVStationById(@PathVariable Long id) {
        return ResponseEntity.ok(evStationService.getEVStationById(id));
    }

    @GetMapping
    public ResponseEntity<List<EVStation>> getAllEVStations() {
        return ResponseEntity.ok(evStationService.getAllEVStations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EVStation> updateEVStation(@PathVariable Long id, @RequestBody EVStation evStation) {
        return ResponseEntity.ok(evStationService.updateEVStation(id, evStation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEVStation(@PathVariable Long id) {
        evStationService.deleteEVStation(id);
        return ResponseEntity.noContent().build();
    }
}
