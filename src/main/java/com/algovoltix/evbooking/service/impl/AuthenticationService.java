package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.AuthenticationRequest;
import com.algovoltix.evbooking.dto.request.RegisterRequest;
import com.algovoltix.evbooking.dto.response.AuthenticationResponse;
import com.algovoltix.evbooking.entity.User;
import com.algovoltix.evbooking.entity.enums.Role;
import com.algovoltix.evbooking.exception.CommonExceptions;
import com.algovoltix.evbooking.repository.UserRepository;
import com.algovoltix.evbooking.service.IAuthenticationService;
import com.algovoltix.evbooking.service.JwtService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest registerRequest) {
    log.info("Registering user with email: {}", registerRequest.getEmail());
    if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
      log.error("Registration failed: Email {} already exists", registerRequest.getEmail());
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
    log.info("User registered successfully with email: {}", registerRequest.getEmail());
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .authenticationToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    log.info("Authenticating user with email: {}", authenticationRequest.getEmail());
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
      );
    } catch (Exception e) {
      log.error("Authentication failed for email: {} - Incorrect password", authenticationRequest.getEmail());
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, CommonExceptions.WRONG_PASSWORD.getMessage());
    }
    var user = userRepository.findByEmail(authenticationRequest.getEmail())
        .orElseThrow(() -> {
          log.error("Authentication failed: User not found with email: {}", authenticationRequest.getEmail());
          return new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.USER_NOT_FOUND.getMessage());
        });
    log.info("User authenticated successfully with email: {}", authenticationRequest.getEmail());
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .authenticationToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse refreshToken(String refreshToken) {
    log.info("Refreshing token");
    if (!jwtService.validateToken(refreshToken)) {
      log.error("Refresh token validation failed");
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
    }
    var user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken))
        .orElseThrow(() -> {
          log.error("Refresh token failed: User not found");
          return new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.USER_NOT_FOUND.getMessage());
        });
    log.info("Token refreshed successfully for user with email: {}", user.getEmail());
    var jwtToken = jwtService.generateToken(user);
    var newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .authenticationToken(jwtToken)
        .refreshToken(newRefreshToken)
        .build();
  }

  public Boolean validateToken(String token) {
    log.info("Validating token");
    if (!jwtService.validateToken(token)) {
      log.error("Token validation failed");
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
    }
    log.info("Token validated successfully");
    return true;
  }
}