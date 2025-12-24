package com.algovoltix.evbooking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {
  private Long userId;
  private String carType;
  private String carNumber;
}
