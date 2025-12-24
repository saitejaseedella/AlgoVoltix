package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionResponse {
    private Long transactionId;
    private Long walletId;
    private String type;
    private String source;
    private Double amount;
}

