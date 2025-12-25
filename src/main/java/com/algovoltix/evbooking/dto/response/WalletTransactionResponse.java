package com.algovoltix.evbooking.dto.response;

import lombok.Data;
import lombok.Builder;

import java.util.UUID;

@Data
@Builder
public class WalletTransactionResponse {
    private UUID transactionId;
    private UUID walletId;
    private Double amount;
    private String type;
    private String source;
    private UUID bookingId;
    private UUID paymentId;
    private UUID transactionBy;
}
