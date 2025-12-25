package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.RateCardRequest;
import com.algovoltix.evbooking.dto.response.RateCardResponse;
import java.util.List;
import java.util.UUID;

public interface RateCardService {
    RateCardResponse createRateCard(RateCardRequest request);
    RateCardResponse getRateCardById(UUID id);
    List<RateCardResponse> getAllRateCards();
    RateCardResponse updateRateCard(UUID id, RateCardRequest request);
    void deleteRateCard(UUID id);
}
