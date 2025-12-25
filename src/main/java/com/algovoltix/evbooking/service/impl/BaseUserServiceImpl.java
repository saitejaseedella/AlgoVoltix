package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.entity.BaseUser;
import com.algovoltix.evbooking.repository.BaseUserRepository;
import com.algovoltix.evbooking.service.BaseUserService;
import com.algovoltix.evbooking.dto.request.UserRequest;
import com.algovoltix.evbooking.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.algovoltix.evbooking.entity.enums.UserType;
import com.algovoltix.evbooking.repository.CustomerRepository;
import com.algovoltix.evbooking.entity.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.algovoltix.evbooking.service.JwtService;
import org.springframework.transaction.annotation.Transactional;
import com.algovoltix.evbooking.repository.UserRepository;
import com.algovoltix.evbooking.entity.User;
import com.algovoltix.evbooking.entity.enums.Role;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseUserServiceImpl implements BaseUserService {
    private final BaseUserRepository baseUserRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        BaseUser user = new BaseUser();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setUserType(userRequest.getUserType());
        BaseUser saved = baseUserRepository.save(user);
        BaseUser managedUser = baseUserRepository.findById(saved.getUserId())
            .orElseThrow(() -> new IllegalStateException("User not found after save"));
        log.info("Creating user: {}", user.getEmail());
        if (userRequest.getUserType() == UserType.CUSTOMER) {
            Customer customer = new Customer();
            customer.setBaseUser(managedUser);
            customerRepository.save(customer);
        }
        // Create User entity for authentication
        if (userRequest.getUserType() == null) {
            log.error("UserType is null in registration request for email: {}", userRequest.getEmail());
            throw new IllegalArgumentException("UserType must not be null");
        }
        Role securityRole = userRequest.getUserType() == UserType.STATION_OWNER ? Role.STATION_OWNER
            : Role.CUSTOMER;
      User securityUser = User.builder()
            .email(userRequest.getEmail())
            .password(passwordEncoder.encode(userRequest.getPassword()))
            .role(securityRole)
            .firstName(userRequest.getName())
            .build();
        userRepository.save(securityUser);
        // Load the saved User entity for JWT generation
        User savedSecurityUser = userRepository.findByEmail(userRequest.getEmail())
            .orElseThrow(() -> new IllegalStateException("Security user not found after save"));
        String jwtToken = jwtService.generateToken(savedSecurityUser);
        return UserResponse.builder()
            .userId(saved.getUserId())
            .name(saved.getName())
            .email(saved.getEmail())
            .jwtToken(jwtToken)
            .build();
    }

    @Override
    public UserResponse getUserById(Long id) {
        BaseUser user = baseUserRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return UserResponse.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return baseUserRepository.findAll().stream()
            .map(user -> UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build())
            .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        BaseUser user = baseUserRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        // user.setPassword(userRequest.getPassword()); // if password exists
        BaseUser saved = baseUserRepository.save(user);
        log.info("Updating user: {}", id);
        return UserResponse.builder()
            .userId(saved.getUserId())
            .name(saved.getName())
            .email(saved.getEmail())
            .build();
    }

    @Override
    public void deleteUser(Long id) {
        if (!baseUserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        baseUserRepository.deleteById(id);
        log.info("Deleted user: {}", id);
    }
}
