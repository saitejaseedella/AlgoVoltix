package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.PaymentController;
import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import com.algovoltix.evbooking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<PaymentResponse> createPayment(PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @Override
    public ResponseEntity<PaymentResponse> getPaymentById(UUID id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @Override
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @Override
    public ResponseEntity<PaymentResponse> updatePayment(UUID id, PaymentRequest request) {
        return ResponseEntity.ok(paymentService.updatePayment(id, request));
    }

    @Override
    public ResponseEntity<Void> deletePayment(UUID id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
