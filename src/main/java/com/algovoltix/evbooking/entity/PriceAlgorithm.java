package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PriceAlgorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long algorithmId;

    private String algorithmType;
    private String config;

    public String getType() {
        return this.algorithmType;
    }
    public void setType(String type) {
        this.algorithmType = type;
    }
    public String getConfig() {
        return this.config;
    }
    public void setConfig(String config) {
        this.config = config;
    }
}
