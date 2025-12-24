package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.CustomerRequest;
import com.algovoltix.evbooking.dto.response.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    void deleteCustomer(Long id);
}
