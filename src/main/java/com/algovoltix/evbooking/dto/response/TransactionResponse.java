package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private Long transactionId;
    private Long walletId;
    private String type;
    private String source;
    private Double amount;
}

