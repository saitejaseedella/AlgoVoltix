package com.algovoltix.evbooking.dto.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequest {
  private UUID userId;
  private String carType;
  private String carNumber;
}
