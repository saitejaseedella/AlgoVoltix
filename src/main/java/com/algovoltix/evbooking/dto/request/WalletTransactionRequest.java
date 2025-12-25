package com.algovoltix.evbooking.dto.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletTransactionRequest {
    private UUID walletId;
    private Double amount;
    private String type;
    private String source;
    private UUID bookingId;
    private UUID paymentId;
    private UUID transactionBy;
}
