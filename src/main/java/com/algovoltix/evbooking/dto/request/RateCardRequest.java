package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateCardRequest {
    private Long slotId;
    private Double price;
}

