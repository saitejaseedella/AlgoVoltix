package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.CustomerRequest;
import com.algovoltix.evbooking.dto.response.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Customer", description = "APIs for managing customers")
@RequestMapping("/api/customers")
public interface CustomerController {
    @Operation(summary = "Create Customer", description = "Create a new customer.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request);

    @Operation(summary = "Get Customer by ID", description = "Retrieve a customer by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer found"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomerById(@Parameter(description = "ID of the customer", required = true) @PathVariable UUID id);

    @Operation(summary = "Get All Customers", description = "Retrieve all customers.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of customers")
    })
    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAllCustomers();

    @Operation(summary = "Update Customer", description = "Update an existing customer.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> updateCustomer(@Parameter(description = "ID of the customer", required = true) @PathVariable UUID id, @RequestBody CustomerRequest request);

    @Operation(summary = "Delete Customer", description = "Delete a customer by their ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@Parameter(description = "ID of the customer", required = true) @PathVariable UUID id);
}
