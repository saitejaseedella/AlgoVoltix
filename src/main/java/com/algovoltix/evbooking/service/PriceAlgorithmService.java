package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.PriceAlgorithmRequest;
import com.algovoltix.evbooking.dto.response.PriceAlgorithmResponse;
import java.util.List;

public interface PriceAlgorithmService {
    PriceAlgorithmResponse createPriceAlgorithm(PriceAlgorithmRequest request);
    PriceAlgorithmResponse getPriceAlgorithmById(Long id);
    List<PriceAlgorithmResponse> getAllPriceAlgorithms();
    PriceAlgorithmResponse updatePriceAlgorithm(Long id, PriceAlgorithmRequest request);
    void deletePriceAlgorithm(Long id);
}
