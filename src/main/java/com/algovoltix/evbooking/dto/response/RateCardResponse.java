package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateCardResponse {
    private Long rateCardId;
    private Long slotId;
    private Double price;
}

