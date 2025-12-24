package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.AuthenticationRequest;
import com.algovoltix.evbooking.dto.request.RegisterRequest;
import com.algovoltix.evbooking.dto.response.AuthenticationResponse;

public interface IAuthenticationService {
  AuthenticationResponse register(RegisterRequest registerRequest);
  AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
  AuthenticationResponse refreshToken(String refreshToken);
  Boolean validateToken(String token);
}
