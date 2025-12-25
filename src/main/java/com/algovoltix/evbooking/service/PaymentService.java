package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse getPaymentById(UUID id);
    List<PaymentResponse> getAllPayments();
    PaymentResponse updatePayment(UUID id, PaymentRequest request);
    void deletePayment(UUID id);
}
