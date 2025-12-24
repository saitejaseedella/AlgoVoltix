package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceAlgorithmRequest {
    private String type;
    private String config;
}

