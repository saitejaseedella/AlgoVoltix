package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.CustomerRequest;
import com.algovoltix.evbooking.dto.response.CustomerResponse;
import com.algovoltix.evbooking.entity.Customer;
import com.algovoltix.evbooking.entity.BaseUser;
import com.algovoltix.evbooking.repository.CustomerRepository;
import com.algovoltix.evbooking.repository.BaseUserRepository;
import com.algovoltix.evbooking.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BaseUserRepository baseUserRepository;

  @Transactional
  @Override
  public CustomerResponse createCustomer(CustomerRequest request) {
    log.info("Attempting to create customer for userId={}", request.getUserId());
    BaseUser user = baseUserRepository.findById(request.getUserId())
        .orElseThrow(() -> {
            log.error("User not found for customer creation: userId={}", request.getUserId());
            return com.algovoltix.evbooking.exception.CommonExceptions.USER_NOT_FOUND;
        });
    if (customerRepository.existsById(user.getUserId())) {
      log.error("Customer already exists for userId={}", user.getUserId());
      throw com.algovoltix.evbooking.exception.CommonExceptions.CONFLICT;
    }

    Customer customer = new Customer();
    customer.setBaseUser(user); // managed inside tx
    customer.setCarType(request.getCarType());
    customer.setCarNumber(request.getCarNumber());

    Customer saved = customerRepository.save(customer);
    log.info("Customer created successfully: userId={}", saved.getUserId());

    return CustomerResponse.builder()
        .userId(saved.getUserId())
        .carType(saved.getCarType())
        .carNumber(saved.getCarNumber())
        .build();
  }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        log.info("Fetching customer by id={}", id);
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Customer not found: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.USER_NOT_FOUND;
            });
        return CustomerResponse.builder()
            .userId(customer.getUserId())
            .carType(customer.getCarType())
            .carNumber(customer.getCarNumber())
            .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
            .map(customer -> CustomerResponse.builder()
                .userId(customer.getUserId())
                .carType(customer.getCarType())
                .carNumber(customer.getCarNumber())
                .build())
            .collect(Collectors.toList());
    }

  @Transactional
  @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        log.info("Attempting to update customer: id={}", id);
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Customer not found for update: id={}", id);
                return com.algovoltix.evbooking.exception.CommonExceptions.USER_NOT_FOUND;
            });
        customer.setCarType(request.getCarType());
        customer.setCarNumber(request.getCarNumber());
        Customer saved = customerRepository.save(customer);
        log.info("Customer updated successfully: id={}", id);
        return CustomerResponse.builder()
            .userId(saved.getUserId())
            .carType(saved.getCarType())
            .carNumber(saved.getCarNumber())
            .build();
    }

    @Transactional
    @Override
    public void deleteCustomer(UUID id) {
        log.info("Attempting to delete customer: id={}", id);
        if (!customerRepository.existsById(id)) {
            log.error("Customer not found for delete: id={}", id);
            throw com.algovoltix.evbooking.exception.CommonExceptions.USER_NOT_FOUND;
        }
        customerRepository.deleteById(id);
        log.info("Customer deleted successfully: id={}", id);
    }
}
