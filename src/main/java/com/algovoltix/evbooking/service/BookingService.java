package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingResponse getBookingById(Long id);

    List<BookingResponse> getAllBookings();

    BookingResponse updateBooking(Long id, BookingRequest bookingRequest);

    void deleteBooking(Long id);
}
