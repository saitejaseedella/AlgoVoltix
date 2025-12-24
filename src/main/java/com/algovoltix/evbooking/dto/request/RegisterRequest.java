package com.algovoltix.evbooking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// REMOVE THIS CLASS, REGISTRATION IS NOW HANDLED IN USERREQUEST
public class RegisterRequest{
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
