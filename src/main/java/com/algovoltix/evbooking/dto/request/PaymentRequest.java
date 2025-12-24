package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private Long walletId;
    private String paymentMode;
    private String status;
}

