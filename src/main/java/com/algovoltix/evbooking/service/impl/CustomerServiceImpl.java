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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
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
    BaseUser user = baseUserRepository.findById(request.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    if (customerRepository.existsById(user.getUserId())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already exists for user");
    }

    Customer customer = new Customer();
    customer.setBaseUser(user); // managed inside tx
    customer.setCarType(request.getCarType());
    customer.setCarNumber(request.getCarNumber());

    Customer saved = customerRepository.save(customer);

    return CustomerResponse.builder()
        .userId(saved.getUserId())
        .carType(saved.getCarType())
        .carNumber(saved.getCarNumber())
        .build();
  }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
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
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        customer.setCarType(request.getCarType());
        customer.setCarNumber(request.getCarNumber());
        Customer saved = customerRepository.save(customer);
        log.info("Updated Customer: {}", saved.getUserId());
        return CustomerResponse.builder()
            .userId(saved.getUserId())
            .carType(saved.getCarType())
            .carNumber(saved.getCarNumber())
            .build();
    }

    @Transactional
    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.deleteById(id);
        log.info("Deleted Customer: {}", id);
    }
}
