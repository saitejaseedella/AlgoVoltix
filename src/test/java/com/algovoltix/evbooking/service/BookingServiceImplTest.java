package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import com.algovoltix.evbooking.entity.BaseUser;
import com.algovoltix.evbooking.entity.Booking;
import com.algovoltix.evbooking.entity.Customer;
import com.algovoltix.evbooking.entity.StationSlot;
import com.algovoltix.evbooking.exception.CommonExceptions;
import com.algovoltix.evbooking.repository.BookingRepository;
import com.algovoltix.evbooking.repository.CustomerRepository;
import com.algovoltix.evbooking.repository.StationSlotRepository;
import com.algovoltix.evbooking.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private StationSlotRepository stationSlotRepository;
    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_success() {
        UUID userId = UUID.randomUUID();
        UUID slotId = UUID.randomUUID();
        BookingRequest request = BookingRequest.builder()
                .userId(userId)
                .slotId(slotId)
                .startTime(LocalDateTime.now().toString())
                .endTime(LocalDateTime.now().plusHours(1).toString())
                .build();
        Customer customer = new Customer();
        BaseUser baseUser = new BaseUser();
        baseUser.setUserId(userId);
        customer.setBaseUser(baseUser);
        StationSlot slot = new StationSlot();
        when(customerRepository.findById(userId)).thenReturn(Optional.of(customer));
        when(stationSlotRepository.findById(slotId)).thenReturn(Optional.of(slot));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> {
            Booking b = i.getArgument(0);
            b.setBookingId(UUID.randomUUID());
            return b;
        });
        BookingResponse response = bookingService.createBooking(request);
        assertNotNull(response.getBookingId());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void createBooking_userNotFound() {
        UUID userId = UUID.randomUUID();
        UUID slotId = UUID.randomUUID();
        BookingRequest request = BookingRequest.builder()
                .userId(userId)
                .slotId(slotId)
                .startTime(LocalDateTime.now().toString())
                .endTime(LocalDateTime.now().plusHours(1).toString())
                .build();
        when(customerRepository.findById(userId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.createBooking(request));
        assertEquals(CommonExceptions.USER_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    void getBookingById_success() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        BookingResponse response = bookingService.getBookingById(bookingId);
        assertEquals(bookingId, response.getBookingId());
    }

    @Test
    void getBookingById_notFound() {
        UUID bookingId = UUID.randomUUID();
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.getBookingById(bookingId));
        assertEquals(CommonExceptions.RESOURCE_NOT_FOUND.getMessage(), exception.getMessage());
    }
}
