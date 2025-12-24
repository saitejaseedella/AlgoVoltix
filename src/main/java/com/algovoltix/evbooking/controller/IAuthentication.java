package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.AuthenticationRequest;
import com.algovoltix.evbooking.dto.request.RegisterRequest;
import com.algovoltix.evbooking.dto.response.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1.0/api/auth")
public interface IAuthentication {

  @ApiOperation("use this endpoint to register a new user")
  @PostMapping("/register")
  ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest);


  @ApiOperation("Use this endpoint to log in an existing user")
  @PostMapping("/log-in")
  ResponseEntity<AuthenticationResponse> longInUser(@RequestBody AuthenticationRequest authenticationRequest);


  @ApiOperation("Use this endpoint to refresh JWT tokens")
  @PostMapping("/refresh")
  ResponseEntity<AuthenticationResponse> refresh(@RequestParam("token") String refreshToken);

  @ApiOperation("Use this endpoint to validate JWT tokens")
  @GetMapping("/validateToken")
  ResponseEntity<Boolean> validateToken(@RequestParam("token") String token);
}
