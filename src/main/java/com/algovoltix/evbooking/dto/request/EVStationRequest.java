package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EVStationRequest {
    private String name;
    private String location;
    private int capacity;
}
