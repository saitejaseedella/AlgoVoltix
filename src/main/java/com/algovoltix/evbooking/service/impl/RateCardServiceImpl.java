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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateCardServiceImpl implements RateCardService {
    private final RateCardRepository rateCardRepository;
    private final StationSlotRepository stationSlotRepository;

    @Override
    public RateCardResponse createRateCard(RateCardRequest request) {
        StationSlot slot = stationSlotRepository.findById(request.getSlotId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StationSlot not found"));
        RateCard rateCard = new RateCard();
        rateCard.setSlot(slot);
        rateCard.setPrice(request.getPrice());
        RateCard saved = rateCardRepository.save(rateCard);
        log.info("Created RateCard: {}", saved.getRateCardId());
        return RateCardResponse.builder()
            .rateCardId(saved.getRateCardId())
            .slotId(slot.getSlotId())
            .price(saved.getPrice())
            .build();
    }

    @Override
    public RateCardResponse getRateCardById(Long id) {
        RateCard rateCard = rateCardRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RateCard not found"));
        return RateCardResponse.builder()
            .rateCardId(rateCard.getRateCardId())
            .slotId(rateCard.getSlot().getSlotId())
            .price(rateCard.getPrice())
            .build();
    }

    @Override
    public List<RateCardResponse> getAllRateCards() {
        return rateCardRepository.findAll().stream()
            .map(rateCard -> RateCardResponse.builder()
                .rateCardId(rateCard.getRateCardId())
                .slotId(rateCard.getSlot().getSlotId())
                .price(rateCard.getPrice())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public RateCardResponse updateRateCard(Long id, RateCardRequest request) {
        RateCard rateCard = rateCardRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RateCard not found"));
        rateCard.setPrice(request.getPrice());
        RateCard saved = rateCardRepository.save(rateCard);
        log.info("Updated RateCard: {}", saved.getRateCardId());
        return RateCardResponse.builder()
            .rateCardId(saved.getRateCardId())
            .slotId(saved.getSlot().getSlotId())
            .price(saved.getPrice())
            .build();
    }

    @Override
    public void deleteRateCard(Long id) {
        if (!rateCardRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RateCard not found");
        }
        rateCardRepository.deleteById(id);
        log.info("Deleted RateCard: {}", id);
    }
}

