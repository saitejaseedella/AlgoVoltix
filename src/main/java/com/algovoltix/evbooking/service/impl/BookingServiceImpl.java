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
import java.util.UUID;
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
        log.info("Attempting to create booking for userId={} and slotId={}", bookingRequest.getUserId(), bookingRequest.getSlotId());
        Customer customer = customerRepository.findById(bookingRequest.getUserId())
            .orElseThrow(() -> {
                log.error("User not found for booking: userId={}", bookingRequest.getUserId());
                return CommonExceptions.USER_NOT_FOUND;
            });
        StationSlot slot = stationSlotRepository.findById(bookingRequest.getSlotId())
            .orElseThrow(() -> {
                log.error("Slot not found or already booked: slotId={}", bookingRequest.getSlotId());
                return CommonExceptions.SLOT_ALREADY_BOOKED;
            });
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setStationSlot(slot);
        booking.setStartTime(LocalDateTime.parse(bookingRequest.getStartTime()));
        booking.setEndTime(LocalDateTime.parse(bookingRequest.getEndTime()));
        booking.setBookingStatus("PENDING");
        booking.setTotalPrice(0.0);
        bookingRepository.save(booking);
        log.info("Booking created successfully: bookingId={}", booking.getBookingId());
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .userId(booking.getCustomer() != null ? booking.getCustomer().getBaseUser().getUserId() : null)
            .slotId(booking.getStationSlot() != null ? booking.getStationSlot().getSlotId() : null)
            .startTime(booking.getStartTime() != null ? booking.getStartTime().toString() : null)
            .endTime(booking.getEndTime() != null ? booking.getEndTime().toString() : null)
            .bookingStatus(booking.getBookingStatus())
            .totalPrice(booking.getTotalPrice())
            .duration(booking.getDuration())
            .createdAt(booking.getCreatedAt())
            .updatedAt(booking.getUpdatedAt())
            .build();
    }

    @Override
    public BookingResponse getBookingById(UUID id) {
        log.info("Fetching booking by id={}", id);
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Booking not found: id={}", id);
                return CommonExceptions.RESOURCE_NOT_FOUND;
            });
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .userId(booking.getCustomer() != null ? booking.getCustomer().getBaseUser().getUserId() : null)
            .slotId(booking.getStationSlot() != null ? booking.getStationSlot().getSlotId() : null)
            .startTime(booking.getStartTime() != null ? booking.getStartTime().toString() : null)
            .endTime(booking.getEndTime() != null ? booking.getEndTime().toString() : null)
            .bookingStatus(booking.getBookingStatus())
            .totalPrice(booking.getTotalPrice())
            .duration(booking.getDuration())
            .createdAt(booking.getCreatedAt())
            .updatedAt(booking.getUpdatedAt())
            .build();
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        log.info("Fetching all bookings");
        return bookingRepository.findAll().stream()
                .map(booking -> BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .userId(booking.getCustomer() != null ? booking.getCustomer().getBaseUser().getUserId() : null)
                    .slotId(booking.getStationSlot() != null ? booking.getStationSlot().getSlotId() : null)
                    .startTime(booking.getStartTime() != null ? booking.getStartTime().toString() : null)
                    .endTime(booking.getEndTime() != null ? booking.getEndTime().toString() : null)
                    .bookingStatus(booking.getBookingStatus())
                    .totalPrice(booking.getTotalPrice())
                    .duration(booking.getDuration())
                    .createdAt(booking.getCreatedAt())
                    .updatedAt(booking.getUpdatedAt())
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse updateBooking(UUID id, BookingRequest bookingRequest) {
        log.info("Attempting to update booking: id={}", id);
        if (!bookingRepository.existsById(id)) {
            log.error("Booking not found for update: id={}", id);
            throw CommonExceptions.RESOURCE_NOT_FOUND;
        }
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> CommonExceptions.RESOURCE_NOT_FOUND);
        if (bookingRequest.getStartTime() != null) booking.setStartTime(LocalDateTime.parse(bookingRequest.getStartTime()));
        if (bookingRequest.getEndTime() != null) booking.setEndTime(LocalDateTime.parse(bookingRequest.getEndTime()));
        bookingRepository.save(booking);
        log.info("Booking updated successfully: id={}", id);
        return BookingResponse.builder()
            .bookingId(booking.getBookingId())
            .userId(booking.getCustomer() != null ? booking.getCustomer().getBaseUser().getUserId() : null)
            .slotId(booking.getStationSlot() != null ? booking.getStationSlot().getSlotId() : null)
            .startTime(booking.getStartTime() != null ? booking.getStartTime().toString() : null)
            .endTime(booking.getEndTime() != null ? booking.getEndTime().toString() : null)
            .bookingStatus(booking.getBookingStatus())
            .totalPrice(booking.getTotalPrice())
            .duration(booking.getDuration())
            .createdAt(booking.getCreatedAt())
            .updatedAt(booking.getUpdatedAt())
            .build();
    }

    @Override
    public void deleteBooking(UUID id) {
        log.info("Attempting to delete booking: id={}", id);
        if (!bookingRepository.existsById(id)) {
            log.error("Booking not found for delete: id={}", id);
            throw CommonExceptions.RESOURCE_NOT_FOUND;
        }
        bookingRepository.deleteById(id);
        log.info("Booking deleted successfully: id={}", id);
    }
}
