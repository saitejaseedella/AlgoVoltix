package com.algovoltix.evbooking.service;


import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

  String extractUserName(String token);

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  String generateToken(UserDetails userDetails);

  String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

  String generateRefresh(Map<String, Object> extraClaims, UserDetails userDetails);

  Boolean isTokenValid(String token, UserDetails userDetails);

  String getEmailFromToken(String token);

  Boolean validateToken(String token);
}
