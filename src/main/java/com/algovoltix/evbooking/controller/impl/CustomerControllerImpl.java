package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.CustomerController;
import com.algovoltix.evbooking.dto.request.CustomerRequest;
import com.algovoltix.evbooking.dto.response.CustomerResponse;
import com.algovoltix.evbooking.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "APIs for managing customers")
public class CustomerControllerImpl implements CustomerController {
    private final CustomerService customerService;

    @Override
    @Operation(summary = "Create Customer", description = "Create a new customer.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @Override
    @Operation(summary = "Get Customer by ID", description = "Retrieve a customer by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer found"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponse> getCustomerById(@Parameter(description = "ID of the customer", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Override
    @Operation(summary = "Get All Customers", description = "Retrieve all customers.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of customers")
    })
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @Operation(summary = "Update Customer", description = "Update an existing customer.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @Override
    @Operation(summary = "Delete Customer", description = "Delete a customer by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<Void> deleteCustomer(@Parameter(description = "ID of the customer", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
