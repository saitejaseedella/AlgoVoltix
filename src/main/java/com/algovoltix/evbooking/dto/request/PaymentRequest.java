package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PaymentRequest {
    private UUID walletId;
    private UUID bookingId;
    private Double amount;
    private String paymentMode;
    private String status;
    private String providerRef;
}
