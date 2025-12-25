package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseUserRepository extends JpaRepository<BaseUser, UUID> {
}
