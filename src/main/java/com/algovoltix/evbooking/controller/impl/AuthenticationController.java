package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.IAuthentication;
import com.algovoltix.evbooking.dto.request.AuthenticationRequest;
import com.algovoltix.evbooking.dto.request.RegisterRequest;
import com.algovoltix.evbooking.dto.response.AuthenticationResponse;
import com.algovoltix.evbooking.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements IAuthentication {
  @Autowired
  private final AuthenticationService authenticationService;

  public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(authenticationService.register(registerRequest));
  }

  public ResponseEntity<AuthenticationResponse> longInUser(AuthenticationRequest authenticationRequest) {
      AuthenticationResponse res = authenticationService.authenticate(authenticationRequest);
      return ResponseEntity.ok(res);
  }

  public ResponseEntity<AuthenticationResponse> refresh(String refreshToken) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(authenticationService.refreshToken(refreshToken));
  }

  public ResponseEntity<Boolean> validateToken(String token) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(authenticationService.validateToken(token));
  }

}