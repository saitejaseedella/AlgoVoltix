package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Basic CRUD methods are provided by JpaRepository
}
