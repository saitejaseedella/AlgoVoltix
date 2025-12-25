package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.UserRequest;
import com.algovoltix.evbooking.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Base User", description = "APIs for managing base users")
@RequestMapping("/api/users")
public interface BaseUserController {
    @Operation(summary = "Create User", description = "Create a new user.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);

    @Operation(summary = "Get User by ID", description = "Retrieve a user by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@Parameter(description = "ID of the user", required = true) @PathVariable UUID id);

    @Operation(summary = "Get All Users", description = "Retrieve all users.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of users")
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(summary = "Update User", description = "Update an existing user.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@Parameter(description = "ID of the user", required = true) @PathVariable UUID id, @RequestBody UserRequest userRequest);

    @Operation(summary = "Delete User", description = "Delete a user by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@Parameter(description = "ID of the user", required = true) @PathVariable UUID id);
}
