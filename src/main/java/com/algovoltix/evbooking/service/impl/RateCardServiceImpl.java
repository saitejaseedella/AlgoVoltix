package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.RateCardRequest;
import com.algovoltix.evbooking.dto.response.RateCardResponse;
import com.algovoltix.evbooking.entity.RateCard;
import com.algovoltix.evbooking.entity.StationSlot;
import com.algovoltix.evbooking.repository.RateCardRepository;
import com.algovoltix.evbooking.repository.StationSlotRepository;
import com.algovoltix.evbooking.service.RateCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateCardServiceImpl implements RateCardService {
    private final RateCardRepository rateCardRepository;
    private final StationSlotRepository stationSlotRepository;

    @Override
    public RateCardResponse createRateCard(RateCardRequest request) {
        log.info("Attempting to create rate card for slotId={}", request.getSlotId());
        StationSlot slot = stationSlotRepository.findById(request.getSlotId())
            .orElseThrow(() -> {
                log.error("StationSlot not found for rate card creation: slotId={}", request.getSlotId());
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        RateCard rateCard = new RateCard();
        rateCard.setSlot(slot);
        rateCard.setPrice(request.getPrice());
        RateCard saved = rateCardRepository.save(rateCard);
        log.info("RateCard created successfully: rateCardId={}", saved.getRateCardId());
        return RateCardResponse.builder()
            .rateCardId(saved.getRateCardId())
            .slotId(slot.getSlotId())
            .price(saved.getPrice())
            .build();
    }

    @Override
    public RateCardResponse getRateCardById(UUID id) {
        log.info("Fetching rate card by id={}", id);
        RateCard rateCard = rateCardRepository.findById(id)
            .orElseThrow(() -> {
                log.error("RateCard not found: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        return RateCardResponse.builder()
            .rateCardId(rateCard.getRateCardId())
            .slotId(rateCard.getSlot().getSlotId())
            .price(rateCard.getPrice())
            .build();
    }

    @Override
    public List<RateCardResponse> getAllRateCards() {
        log.info("Fetching all rate cards");
        return rateCardRepository.findAll().stream()
            .map(rateCard -> RateCardResponse.builder()
                .rateCardId(rateCard.getRateCardId())
                .slotId(rateCard.getSlot().getSlotId())
                .price(rateCard.getPrice())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public RateCardResponse updateRateCard(UUID id, RateCardRequest request) {
        log.info("Attempting to update rate card: id={}", id);
        RateCard rateCard = rateCardRepository.findById(id)
            .orElseThrow(() -> {
                log.error("RateCard not found for update: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
            });
        rateCard.setPrice(request.getPrice());
        RateCard saved = rateCardRepository.save(rateCard);
        log.info("RateCard updated successfully: id={}", id);
        return RateCardResponse.builder()
            .rateCardId(saved.getRateCardId())
            .slotId(saved.getSlot().getSlotId())
            .price(saved.getPrice())
            .build();
    }

    @Override
    public void deleteRateCard(UUID id) {
        log.info("Attempting to delete rate card: id={}", id);
        if (!rateCardRepository.existsById(id)) {
            log.error("RateCard not found for delete: id={}", id);
            throw com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND;
        }
        rateCardRepository.deleteById(id);
        log.info("RateCard deleted successfully: id={}", id);
    }
}
