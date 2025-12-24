package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentRequest {
    private Long walletId;
    private String paymentMode;
    private String status;
}

