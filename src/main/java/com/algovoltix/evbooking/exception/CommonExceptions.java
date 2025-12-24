package com.algovoltix.evbooking.exception;

import org.springframework.http.HttpStatus;

public class CommonExceptions extends VoltixExceptions{

  public CommonExceptions(HttpStatus httpStatus,String code,  String message) {
    super(code, httpStatus, message);
  }

  public static final CommonExceptions  UNAUTHORIZED_USER =
      new CommonExceptions(HttpStatus.UNAUTHORIZED,"401","User is not authorized to perform this action");

  public static final CommonExceptions RESOURCE_NOT_FOUND =
      new CommonExceptions(HttpStatus.NOT_FOUND, "404", "The requested resource was not found");

  public static final CommonExceptions BAD_REQUEST =
      new CommonExceptions(HttpStatus.BAD_REQUEST, "400", "The request is invalid or malformed");

  public static final CommonExceptions INTERNAL_SERVER_ERROR =
      new CommonExceptions(HttpStatus.INTERNAL_SERVER_ERROR, "500", "An unexpected error occurred on the server");

  public static final CommonExceptions FORBIDDEN_ACTION =
      new CommonExceptions(HttpStatus.FORBIDDEN, "403", "You do not have permission to perform this action");

  public static final CommonExceptions CONFLICT =
      new CommonExceptions(HttpStatus.CONFLICT, "409", "The request could not be completed due to a conflict with the current state of the resource");

  public static final CommonExceptions WRONG_PASSWORD =
      new CommonExceptions(HttpStatus.UNAUTHORIZED, "401", "The provided password is incorrect");

  public static final CommonExceptions EMAIL_ALREADY_VERIFIED =
      new CommonExceptions(HttpStatus.BAD_REQUEST, "400", "The email address is already verified");

  public static final CommonExceptions ACCOUNT_NOT_FOUND =
      new CommonExceptions(HttpStatus.NOT_FOUND, "404", "The specified account could not be found");

  public static final CommonExceptions SLOT_ALREADY_BOOKED =
      new CommonExceptions(HttpStatus.CONFLICT, "409", "The selected slot is already booked");

  public static final CommonExceptions INSUFFICIENT_BALANCE =
      new CommonExceptions(HttpStatus.BAD_REQUEST, "400", "Insufficient balance in the wallet");

  public static final CommonExceptions INVALID_BOOKING_DETAILS =
      new CommonExceptions(HttpStatus.BAD_REQUEST, "400", "The booking details provided are invalid");

  public static final CommonExceptions USER_NOT_FOUND =
      new CommonExceptions(HttpStatus.NOT_FOUND, "404", "The specified user could not be found");

  public static final CommonExceptions WALLET_NOT_FOUND =
      new CommonExceptions(HttpStatus.NOT_FOUND, "404", "The specified wallet could not be found");

  public static final CommonExceptions EV_STATION_NOT_FOUND =
      new CommonExceptions(HttpStatus.NOT_FOUND, "404", "The specified EV station could not be found");
}
