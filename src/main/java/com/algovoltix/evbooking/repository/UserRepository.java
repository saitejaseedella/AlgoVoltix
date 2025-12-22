package com.algovoltix.evbooking.repository;

import com.algovoltix.evbooking.entity.User;
import com.algovoltix.evbooking.entity.enums.Role;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
  Optional<User> findByRole(Role role);
}
