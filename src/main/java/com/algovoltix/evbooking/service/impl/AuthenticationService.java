package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.AuthenticationRequest;
import com.algovoltix.evbooking.dto.request.RegisterRequest;
import com.algovoltix.evbooking.dto.response.AuthenticationResponse;
import com.algovoltix.evbooking.entity.User;
import com.algovoltix.evbooking.entity.enums.Role;
import com.algovoltix.evbooking.exception.CommonExceptions;
import com.algovoltix.evbooking.repository.UserRepository;
import com.algovoltix.evbooking.service.JwtService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest registerRequest) {
    if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, CommonExceptions.CONFLICT.getMessage());
    }
    User user = User.builder()
        .firstName(registerRequest.getFirstName())
        .lastName(registerRequest.getLastName())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(Role.USER)
        .fullName(registerRequest.getFirstName() + " " + registerRequest.getLastName())
        .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .authenticationToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
      );
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, CommonExceptions.WRONG_PASSWORD.getMessage());
    }
    var user = userRepository.findByEmail(authenticationRequest.getEmail())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.USER_NOT_FOUND.getMessage()));
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .authenticationToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse refreshToken(String refreshToken) {
    if (!jwtService.validateToken(refreshToken)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
    }
    var user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.USER_NOT_FOUND.getMessage()));
    var jwtToken = jwtService.generateToken(user);
    var newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .authenticationToken(jwtToken)
        .refreshToken(newRefreshToken)
        .build();
  }

  public Boolean validateToken(String token) {
    if (!jwtService.validateToken(token)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
    }
    return true;
  }
}