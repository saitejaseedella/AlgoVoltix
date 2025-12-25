package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.AuthenticationRequest;
import com.algovoltix.evbooking.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Authentication", description = "APIs for user authentication and JWT management")
@RequestMapping("/v1.0/api/auth")
public interface IAuthenticationController {

  @Operation(summary = "Log in user", description = "Use this endpoint to log in an existing user.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "User logged in successfully"),
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
  })
  @PostMapping("/log-in")
  ResponseEntity<AuthenticationResponse> longInUser(@RequestBody AuthenticationRequest authenticationRequest);

  @Operation(summary = "Refresh JWT token", description = "Use this endpoint to refresh JWT tokens.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid token")
  })
  @PostMapping("/refresh")
  ResponseEntity<AuthenticationResponse> refresh(@RequestParam("token") String refreshToken);

  @Operation(summary = "Validate JWT token", description = "Use this endpoint to validate JWT tokens.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Token is valid"),
    @ApiResponse(responseCode = "400", description = "Token is invalid")
  })
  @GetMapping("/validateToken")
  ResponseEntity<Boolean> validateToken(@RequestParam("token") String token);
}
