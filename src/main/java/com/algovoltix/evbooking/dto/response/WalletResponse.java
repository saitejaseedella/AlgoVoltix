package com.algovoltix.evbooking.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WalletResponse {
    private UUID walletId;
    private UUID userId;
    private Double balance;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
