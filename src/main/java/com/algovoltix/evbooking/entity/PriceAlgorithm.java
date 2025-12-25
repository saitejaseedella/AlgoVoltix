package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class PriceAlgorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID algorithmId;

    private String algorithmType;
    private String config;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public String getType() {
        return this.algorithmType;
    }
    public void setType(String type) {
        this.algorithmType = type;
    }
}
