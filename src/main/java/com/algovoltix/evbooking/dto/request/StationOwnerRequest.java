package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StationOwnerRequest {
    private Long userId;
    private String gstNumber;
}

