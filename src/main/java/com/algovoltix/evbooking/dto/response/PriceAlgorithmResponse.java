package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceAlgorithmResponse {
    private Long algorithmId;
    private String type;
    private String config;
}

