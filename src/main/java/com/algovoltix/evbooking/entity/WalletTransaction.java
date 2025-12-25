package com.algovoltix.evbooking.entity;

import com.algovoltix.evbooking.entity.enums.WalletTransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "wallet_transactions")
public class WalletTransaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private WalletTransactionType walletTransactionType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    private String reference;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private UUID transactionBy; // userId
    private String type; // credit/debit
    private String source; // wallet topup/payment/refund/etc.
}
