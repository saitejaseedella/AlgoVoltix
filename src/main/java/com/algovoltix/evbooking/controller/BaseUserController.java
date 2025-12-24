package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.UserRequest;
import com.algovoltix.evbooking.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface BaseUserController {
    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id);
    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUsers();
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
