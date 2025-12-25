package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.CustomerController;
import com.algovoltix.evbooking.dto.request.CustomerRequest;
import com.algovoltix.evbooking.dto.response.CustomerResponse;
import com.algovoltix.evbooking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CustomerControllerImpl implements CustomerController {
    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> createCustomer(CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomerById(UUID id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(UUID id, CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
