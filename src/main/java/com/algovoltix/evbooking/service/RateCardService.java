package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.RateCardRequest;
import com.algovoltix.evbooking.dto.response.RateCardResponse;
import java.util.List;

public interface RateCardService {
    RateCardResponse createRateCard(RateCardRequest request);
    RateCardResponse getRateCardById(Long id);
    List<RateCardResponse> getAllRateCards();
    RateCardResponse updateRateCard(Long id, RateCardRequest request);
    void deleteRateCard(Long id);
}
