package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse getPaymentById(Long id);
    List<PaymentResponse> getAllPayments();
    PaymentResponse updatePayment(Long id, PaymentRequest request);
    void deletePayment(Long id);
}
