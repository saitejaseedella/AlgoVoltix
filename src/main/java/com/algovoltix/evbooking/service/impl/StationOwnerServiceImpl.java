package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.StationOwnerRequest;
import com.algovoltix.evbooking.dto.response.StationOwnerResponse;
import com.algovoltix.evbooking.entity.StationOwner;
import com.algovoltix.evbooking.entity.BaseUser;
import com.algovoltix.evbooking.repository.StationOwnerRepository;
import com.algovoltix.evbooking.repository.BaseUserRepository;
import com.algovoltix.evbooking.service.StationOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationOwnerServiceImpl implements StationOwnerService {
    private final StationOwnerRepository stationOwnerRepository;
    private final BaseUserRepository baseUserRepository;

    @Override
    public StationOwnerResponse createStationOwner(StationOwnerRequest request) {
        BaseUser user = baseUserRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        StationOwner owner = new StationOwner();
        owner.setBaseUser(user);
        owner.setGstNumber(request.getGstNumber());
        StationOwner saved = stationOwnerRepository.save(owner);
        log.info("Created StationOwner: {}", saved.getUserId());
        return StationOwnerResponse.builder()
            .userId(saved.getUserId())
            .gstNumber(saved.getGstNumber())
            .build();
    }

    @Override
    public StationOwnerResponse getStationOwnerById(Long id) {
        StationOwner owner = stationOwnerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StationOwner not found"));
        return StationOwnerResponse.builder()
            .userId(owner.getUserId())
            .gstNumber(owner.getGstNumber())
            .build();
    }

    @Override
    public List<StationOwnerResponse> getAllStationOwners() {
        return stationOwnerRepository.findAll().stream()
            .map(owner -> StationOwnerResponse.builder()
                .userId(owner.getUserId())
                .gstNumber(owner.getGstNumber())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public StationOwnerResponse updateStationOwner(Long id, StationOwnerRequest request) {
        StationOwner owner = stationOwnerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StationOwner not found"));
        owner.setGstNumber(request.getGstNumber());
        StationOwner saved = stationOwnerRepository.save(owner);
        log.info("Updated StationOwner: {}", saved.getUserId());
        return StationOwnerResponse.builder()
            .userId(saved.getUserId())
            .gstNumber(saved.getGstNumber())
            .build();
    }

    @Override
    public void deleteStationOwner(Long id) {
        if (!stationOwnerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "StationOwner not found");
        }
        stationOwnerRepository.deleteById(id);
        log.info("Deleted StationOwner: {}", id);
    }
}

