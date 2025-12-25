package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.UserRequest;
import com.algovoltix.evbooking.dto.response.UserResponse;
import java.util.List;
import java.util.UUID;

public interface BaseUserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(UUID id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UUID id, UserRequest userRequest);
    void deleteUser(UUID id);
}
