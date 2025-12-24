package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long walletId;
    private String paymentMode;
    private String status;
}

