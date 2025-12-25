package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.PaymentRequest;
import com.algovoltix.evbooking.dto.response.PaymentResponse;
import com.algovoltix.evbooking.entity.Payment;
import com.algovoltix.evbooking.entity.Wallet;
import com.algovoltix.evbooking.repository.PaymentRepository;
import com.algovoltix.evbooking.repository.WalletRepository;
import com.algovoltix.evbooking.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPayment_success() {
        UUID walletId = UUID.randomUUID();
        PaymentRequest request = PaymentRequest.builder()
                .walletId(walletId)
                .paymentMode("UPI")
                .status("SUCCESS")
                .build();
        Wallet wallet = new Wallet();
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> {
            Payment p = i.getArgument(0);
            p.setPaymentId(UUID.randomUUID());
            return p;
        });
        PaymentResponse response = paymentService.createPayment(request);
        assertNotNull(response.getPaymentId());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void createPayment_walletNotFound() {
        UUID walletId = UUID.randomUUID();
        PaymentRequest request = PaymentRequest.builder()
                .walletId(walletId)
                .paymentMode("UPI")
                .status("SUCCESS")
                .build();
        when(walletRepository.findById(walletId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(com.algovoltix.evbooking.exception.CommonExceptions.class, () -> paymentService.createPayment(request));
        assertEquals(com.algovoltix.evbooking.exception.CommonExceptions.WALLET_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    void getPaymentById_success() {
        UUID paymentId = UUID.randomUUID();
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        Wallet wallet = new Wallet();
        payment.setWallet(wallet);
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));
        PaymentResponse response = paymentService.getPaymentById(paymentId);
        assertEquals(paymentId, response.getPaymentId());
    }

    @Test
    void getPaymentById_notFound() {
        UUID paymentId = UUID.randomUUID();
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(com.algovoltix.evbooking.exception.CommonExceptions.class, () -> paymentService.getPaymentById(paymentId));
        assertEquals(com.algovoltix.evbooking.exception.CommonExceptions.RESOURCE_NOT_FOUND.getMessage(), exception.getMessage());
    }
}
