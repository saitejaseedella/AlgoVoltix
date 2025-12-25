package com.algovoltix.evbooking.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceAlgorithmResponse {
    private UUID algorithmId;
    private String algorithmType;
    private String config;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
