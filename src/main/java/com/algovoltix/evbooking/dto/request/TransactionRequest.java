package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionRequest {
    private Long walletId;
    private String type;
    private String source;
    private Double amount;
}

