package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Wallet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long walletId;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", unique = true, nullable = false)
  private BaseUser user;

  private Double balance;
}

