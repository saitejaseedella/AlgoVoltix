package com.algovoltix.evbooking.dto.response;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EVStationResponse {
    private Long id;
    private String name;
    private String location;
    private int capacity;
}
