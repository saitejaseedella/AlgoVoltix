package com.algovoltix.evbooking.service.impl;

import com.algovoltix.evbooking.dto.request.BookingRequest;
import com.algovoltix.evbooking.dto.response.BookingResponse;
import com.algovoltix.evbooking.entity.Booking;
import com.algovoltix.evbooking.entity.Customer;
import com.algovoltix.evbooking.entity.StationSlot;
import com.algovoltix.evbooking.exception.CommonExceptions;
import com.algovoltix.evbooking.repository.BookingRepository;
import com.algovoltix.evbooking.repository.CustomerRepository;
import com.algovoltix.evbooking.repository.StationSlotRepository;
import com.algovoltix.evbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final StationSlotRepository stationSlotRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Customer customer = customerRepository.findById(bookingRequest.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        StationSlot slot = stationSlotRepository.findById(bookingRequest.getSlotId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found"));
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setStationSlot(slot);
        booking.setStartTime(LocalDateTime.parse(bookingRequest.getStartTime()));
        booking.setEndTime(LocalDateTime.parse(bookingRequest.getEndTime()));
        booking.setBookingStatus("PENDING");
        booking.setTotalPrice(0.0); // Pricing logic can be added
        bookingRepository.save(booking);
        log.info("Booking created: {}", booking.getBookingId());
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .status(booking.getBookingStatus())
            .message("Booking created successfully")
            .build();
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.RESOURCE_NOT_FOUND.getMessage()));
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .status(booking.getBookingStatus())
            .message("Booking fetched successfully")
            .build();
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .status(booking.getBookingStatus())
                    .message("Booking fetched successfully")
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse updateBooking(Long id, BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.RESOURCE_NOT_FOUND.getMessage()));
        if (bookingRequest.getStartTime() != null) booking.setStartTime(LocalDateTime.parse(bookingRequest.getStartTime()));
        if (bookingRequest.getEndTime() != null) booking.setEndTime(LocalDateTime.parse(bookingRequest.getEndTime()));
        bookingRepository.save(booking);
        log.info("Booking updated: {}", booking.getBookingId());
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .status(booking.getBookingStatus())
            .message("Booking updated successfully")
            .build();
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, CommonExceptions.RESOURCE_NOT_FOUND.getMessage());
        }
        bookingRepository.deleteById(id);
        log.info("Booking deleted: {}", id);
    }
}
