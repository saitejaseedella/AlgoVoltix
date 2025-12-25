package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.BaseUserController;
import com.algovoltix.evbooking.dto.request.UserRequest;
import com.algovoltix.evbooking.dto.response.UserResponse;
import com.algovoltix.evbooking.service.BaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BaseUserControllerImpl implements BaseUserController {
    private final BaseUserService baseUserService;

    @Override
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(baseUserService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(baseUserService.getUserById(id));
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(baseUserService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(baseUserService.updateUser(id, userRequest));
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        baseUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
