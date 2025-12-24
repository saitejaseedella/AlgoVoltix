package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.entity.EVStation;
import com.algovoltix.evbooking.service.EVStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/ev-stations")
public interface EVStationController {

    @PostMapping
    ResponseEntity<EVStation> createEVStation(@RequestBody EVStation evStation);

    @GetMapping("/{id}")
    ResponseEntity<EVStation> getEVStationById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<EVStation>> getAllEVStations();

    @PutMapping("/{id}")
    ResponseEntity<EVStation> updateEVStation(@PathVariable Long id, @RequestBody EVStation evStation);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEVStation(@PathVariable Long id);
}
