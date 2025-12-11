package com.algovoltix.evbooking.service;

import java.util.List;
import com.algovoltix.evbooking.entity.EVStation;

public interface EVStationService {

  void deleteEVStation(Long id);
  EVStation updateEVStation(Long id, EVStation evStation);
  List<EVStation> getAllEVStations();
  EVStation getEVStationById(Long id);
  EVStation createEVStation(EVStation evStation);
}


