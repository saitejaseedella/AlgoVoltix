package com.algovoltix.evbooking.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentResponse {
    private UUID paymentId;
    private UUID walletId;
    private UUID bookingId;
    private Double amount;
    private String paymentMode;
    private String status;
    private String providerRef;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
