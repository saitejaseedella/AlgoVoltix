package com.algovoltix.evbooking.exception;

import org.springframework.http.HttpStatus;

public class VoltixExceptions extends RuntimeException {

  private final String code;
  private final HttpStatus HttpStatus;
  private final String message;

  public VoltixExceptions(String code, HttpStatus httpStatus, String message) {
    this.code = code;
    this.HttpStatus = httpStatus;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return HttpStatus;
  }

  public String getMessage() {
    return message;
  }
}
