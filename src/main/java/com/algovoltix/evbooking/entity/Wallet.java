package com.algovoltix.evbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Wallet {

  @Id
  private UUID walletId;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", unique = true, nullable = false)
  private BaseUser user;

  private Double balance;

  @CreationTimestamp
  @Column(updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;
}
