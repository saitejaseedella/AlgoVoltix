package com.algovoltix.evbooking.dto.request;

import com.algovoltix.evbooking.entity.enums.UserType;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private UserType userType;
}
