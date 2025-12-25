package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private String type;
    private String source;

    @ManyToOne
    @JoinColumn(name = "transaction_by")
    private BaseUser transactionBy;

    private Double amount;
    public Double getAmount() {
        return this.amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
