package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.PriceAlgorithmRequest;
import com.algovoltix.evbooking.dto.response.PriceAlgorithmResponse;
import com.algovoltix.evbooking.entity.PriceAlgorithm;
import com.algovoltix.evbooking.repository.PriceAlgorithmRepository;
import com.algovoltix.evbooking.service.PriceAlgorithmService;
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
public class PriceAlgorithmServiceImpl implements PriceAlgorithmService {
    private final PriceAlgorithmRepository priceAlgorithmRepository;

    @Override
    public PriceAlgorithmResponse createPriceAlgorithm(PriceAlgorithmRequest request) {
        PriceAlgorithm algorithm = new PriceAlgorithm();
        algorithm.setType(request.getType());
        algorithm.setConfig(request.getConfig());
        PriceAlgorithm saved = priceAlgorithmRepository.save(algorithm);
        log.info("Created PriceAlgorithm: {}", saved.getAlgorithmId());
        return PriceAlgorithmResponse.builder()
            .algorithmId(saved.getAlgorithmId())
            .type(saved.getType())
            .config(saved.getConfig())
            .build();
    }

    @Override
    public PriceAlgorithmResponse getPriceAlgorithmById(Long id) {
        PriceAlgorithm algorithm = priceAlgorithmRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PriceAlgorithm not found"));
        return PriceAlgorithmResponse.builder()
            .algorithmId(algorithm.getAlgorithmId())
            .type(algorithm.getType())
            .config(algorithm.getConfig())
            .build();
    }

    @Override
    public List<PriceAlgorithmResponse> getAllPriceAlgorithms() {
        return priceAlgorithmRepository.findAll().stream()
            .map(algorithm -> PriceAlgorithmResponse.builder()
                .algorithmId(algorithm.getAlgorithmId())
                .type(algorithm.getType())
                .config(algorithm.getConfig())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public PriceAlgorithmResponse updatePriceAlgorithm(Long id, PriceAlgorithmRequest request) {
        PriceAlgorithm algorithm = priceAlgorithmRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PriceAlgorithm not found"));
        algorithm.setType(request.getType());
        algorithm.setConfig(request.getConfig());
        PriceAlgorithm saved = priceAlgorithmRepository.save(algorithm);
        log.info("Updated PriceAlgorithm: {}", saved.getAlgorithmId());
        return PriceAlgorithmResponse.builder()
            .algorithmId(saved.getAlgorithmId())
            .type(saved.getType())
            .config(saved.getConfig())
            .build();
    }

    @Override
    public void deletePriceAlgorithm(Long id) {
        if (!priceAlgorithmRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PriceAlgorithm not found");
        }
        priceAlgorithmRepository.deleteById(id);
        log.info("Deleted PriceAlgorithm: {}", id);
    }
}

