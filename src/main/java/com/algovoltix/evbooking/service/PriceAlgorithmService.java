package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.PriceAlgorithmRequest;
import com.algovoltix.evbooking.dto.response.PriceAlgorithmResponse;
import java.util.List;
import java.util.UUID;

public interface PriceAlgorithmService {
    PriceAlgorithmResponse createPriceAlgorithm(PriceAlgorithmRequest request);
    PriceAlgorithmResponse getPriceAlgorithmById(UUID id);
    List<PriceAlgorithmResponse> getAllPriceAlgorithms();
    PriceAlgorithmResponse updatePriceAlgorithm(UUID id, PriceAlgorithmRequest request);
    void deletePriceAlgorithm(UUID id);
}
