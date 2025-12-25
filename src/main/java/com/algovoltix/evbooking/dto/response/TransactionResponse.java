package com.algovoltix.evbooking.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionResponse {
    private UUID transactionId;
    private UUID walletId;
    private UUID bookingId;
    private UUID paymentId;
    private UUID transactionBy;
    private String type;
    private String source;
    private Double amount;
    private java.util.Date createdAt;
}
