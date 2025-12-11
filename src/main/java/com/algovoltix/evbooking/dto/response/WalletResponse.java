package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletResponse {
    private Long walletId;
    private Double balance;
}
