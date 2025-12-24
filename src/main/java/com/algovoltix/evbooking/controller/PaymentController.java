package com.algovoltix.evbooking.controller;

import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Payment", description = "APIs for managing payments")
public interface PaymentController {
    @Operation(summary = "Create Payment", description = "Create a new payment.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request);

    @Operation(summary = "Get Payment by ID", description = "Retrieve a payment by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> getPaymentById(@Parameter(description = "ID of the payment", required = true) @PathVariable Long id);

    @Operation(summary = "Get All Payments", description = "Retrieve all payments.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of payments")
    })
    @GetMapping
    ResponseEntity<List<PaymentResponse>> getAllPayments();

    @Operation(summary = "Update Payment", description = "Update an existing payment.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment updated successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<PaymentResponse> updatePayment(@Parameter(description = "ID of the payment", required = true) @PathVariable Long id, @RequestBody PaymentRequest request);

    @Operation(summary = "Delete Payment", description = "Delete a payment by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePayment(@Parameter(description = "ID of the payment", required = true) @PathVariable Long id);
}
