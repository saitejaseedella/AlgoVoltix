package com.algovoltix.evbooking.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatus {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED");

    private final String value;

    BookingStatus(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

}