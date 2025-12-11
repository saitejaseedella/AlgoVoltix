package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EVStationResponse {
    private Long id;
    private String name;
    private String location;
    private int capacity;
}
