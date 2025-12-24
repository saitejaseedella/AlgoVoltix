package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    private String paymentMode;
    private String status;
}
