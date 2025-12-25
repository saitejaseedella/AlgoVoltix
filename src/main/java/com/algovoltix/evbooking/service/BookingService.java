package com.algovoltix.evbooking.service;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
    BookingResponse getBookingById(UUID id);
    List<BookingResponse> getAllBookings();
    BookingResponse updateBooking(UUID id, BookingRequest bookingRequest);
    void deleteBooking(UUID id);
}
