package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateCardResponse {
    private Long rateCardId;
    private Long slotId;
    private Double price;
}

