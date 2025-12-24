package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import com.algovoltix.evbooking.entity.Booking;
import com.algovoltix.evbooking.exception.CommonExceptions;
import com.algovoltix.evbooking.repository.BookingRepository;
import com.algovoltix.evbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        // Map fields from bookingRequest to booking entity
        bookingRepository.save(booking);
        return new BookingResponse(); // Map fields from booking entity to response
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.RESOURCE_NOT_FOUND.getMessage()));
        return new BookingResponse(); // Map fields from booking entity to response
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> new BookingResponse()) // Map fields from booking entity to response
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse updateBooking(Long id, BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.RESOURCE_NOT_FOUND.getMessage()));
        // Update fields in booking entity from bookingRequest
        bookingRepository.save(booking);
        return new BookingResponse(); // Map fields from booking entity to response
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.RESOURCE_NOT_FOUND.getMessage());
        }
        bookingRepository.deleteById(id);
    }
}
