package com.algovoltix.evbooking.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private UUID userId;
    private String name;
    private String email;
    private String address;
    private String mobileNumber;
    private String geoLocation;
    private com.algovoltix.evbooking.entity.enums.UserType userType;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
}
