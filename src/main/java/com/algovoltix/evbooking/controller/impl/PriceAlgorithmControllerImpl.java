package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.PriceAlgorithmController;
import com.algovoltix.evbooking.dto.request.PriceAlgorithmRequest;
import com.algovoltix.evbooking.dto.response.PriceAlgorithmResponse;
import com.algovoltix.evbooking.service.PriceAlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PriceAlgorithmControllerImpl implements PriceAlgorithmController {
    private final PriceAlgorithmService priceAlgorithmService;

    @Override
    public ResponseEntity<PriceAlgorithmResponse> createPriceAlgorithm(PriceAlgorithmRequest request) {
        return ResponseEntity.ok(priceAlgorithmService.createPriceAlgorithm(request));
    }

    @Override
    public ResponseEntity<PriceAlgorithmResponse> getPriceAlgorithmById(UUID id) {
        return ResponseEntity.ok(priceAlgorithmService.getPriceAlgorithmById(id));
    }

    @Override
    public ResponseEntity<List<PriceAlgorithmResponse>> getAllPriceAlgorithms() {
        return ResponseEntity.ok(priceAlgorithmService.getAllPriceAlgorithms());
    }

    @Override
    public ResponseEntity<PriceAlgorithmResponse> updatePriceAlgorithm(UUID id, PriceAlgorithmRequest request) {
        return ResponseEntity.ok(priceAlgorithmService.updatePriceAlgorithm(id, request));
    }

    @Override
    public ResponseEntity<Void> deletePriceAlgorithm(UUID id) {
        priceAlgorithmService.deletePriceAlgorithm(id);
        return ResponseEntity.noContent().build();
    }
}
