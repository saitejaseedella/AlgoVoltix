package com.algovoltix.evbooking.dto.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WalletRequest {
    private UUID userId;
    private Double balance;
}
