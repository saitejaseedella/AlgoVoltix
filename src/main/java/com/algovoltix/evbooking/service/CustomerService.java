package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.CustomerRequest;
import com.algovoltix.evbooking.dto.response.CustomerResponse;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(UUID id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(UUID id, CustomerRequest request);
    void deleteCustomer(UUID id);
}
