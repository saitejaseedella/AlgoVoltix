package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Long userId;
    private String carType;
    private String carNumber;
}

