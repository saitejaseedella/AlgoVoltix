package com.algovoltix.evbooking.entity;

import com.algovoltix.evbooking.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "base_user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"mobileNumber"})
})
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String geoLocation;

    @Column(length = 15, unique = true, nullable = true)
    private String mobileNumber;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
