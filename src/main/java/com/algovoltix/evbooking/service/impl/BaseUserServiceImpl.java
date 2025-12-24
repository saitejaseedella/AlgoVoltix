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

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseUserServiceImpl implements BaseUserService {
    private final BaseUserRepository baseUserRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        BaseUser user = new BaseUser();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        // user.setPassword(userRequest.getPassword()); // if password exists
        BaseUser saved = baseUserRepository.save(user);
        log.info("Creating user: {}", user.getEmail());
        return UserResponse.builder()
            .userId(saved.getUserId())
            .name(saved.getName())
            .email(saved.getEmail())
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
