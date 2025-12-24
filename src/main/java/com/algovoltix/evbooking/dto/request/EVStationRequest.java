package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EVStationRequest {
    private String name;
    private String location;
    private int capacity;
}
