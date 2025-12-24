package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.PaymentController;
import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import com.algovoltix.evbooking.service.PaymentService;
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
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "APIs for managing payments")
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    @Override
    @Operation(summary = "Create Payment", description = "Create a new payment.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @Override
    @Operation(summary = "Get Payment by ID", description = "Retrieve a payment by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<PaymentResponse> getPaymentById(@Parameter(description = "ID of the payment", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @Override
    @Operation(summary = "Get All Payments", description = "Retrieve all payments.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of payments")
    })
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @Override
    @Operation(summary = "Update Payment", description = "Update an existing payment.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Payment updated successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long id, @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.updatePayment(id, request));
    }

    @Override
    @Operation(summary = "Delete Payment", description = "Delete a payment by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<Void> deletePayment(@Parameter(description = "ID of the payment", required = true) @PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
