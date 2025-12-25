package com.algovoltix.evbooking.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateCardResponse {
    private UUID rateCardId;
    private UUID slotId;
    private Double price;
    private UUID algorithmId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
