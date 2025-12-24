package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationOwnerResponse {
    private Long userId;
    private String gstNumber;
}

