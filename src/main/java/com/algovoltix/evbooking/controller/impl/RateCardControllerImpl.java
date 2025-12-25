package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.RateCardController;
import com.algovoltix.evbooking.dto.request.RateCardRequest;
import com.algovoltix.evbooking.dto.response.RateCardResponse;
import com.algovoltix.evbooking.service.RateCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RateCardControllerImpl implements RateCardController {
    private final RateCardService rateCardService;

    @Override
    public ResponseEntity<RateCardResponse> createRateCard(RateCardRequest request) {
        return ResponseEntity.ok(rateCardService.createRateCard(request));
    }

    @Override
    public ResponseEntity<RateCardResponse> getRateCardById(UUID id) {
        return ResponseEntity.ok(rateCardService.getRateCardById(id));
    }

    @Override
    public ResponseEntity<List<RateCardResponse>> getAllRateCards() {
        return ResponseEntity.ok(rateCardService.getAllRateCards());
    }

    @Override
    public ResponseEntity<RateCardResponse> updateRateCard(UUID id, RateCardRequest request) {
        return ResponseEntity.ok(rateCardService.updateRateCard(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteRateCard(UUID id) {
        rateCardService.deleteRateCard(id);
        return ResponseEntity.noContent().build();
    }
}
