package com.algovoltix.evbooking.entity.enums;

import com.algovoltix.evbooking.entity.WalletTransaction;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WalletTransactionType {


  CREDIT("CREDIT"), DEBIT("DEBIT");
  private final String value;

  WalletTransactionType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

}
