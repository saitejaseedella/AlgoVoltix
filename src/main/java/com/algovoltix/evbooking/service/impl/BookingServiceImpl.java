//package com.algovoltix.evbooking.service.impl;
//
//import com.algovoltix.evbooking.dto.BookingResponse;
//import com.algovoltix.evbooking.dto.CreateBookingRequest;
//import com.algovoltix.evbooking.entity.*;
//import com.algovoltix.evbooking.repository.*;
//import com.algovoltix.evbooking.service.BookingService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.List;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    private final UserRepository userRepository;
//    private final SeatRepository seatRepository;
//    private final BookingRepository bookingRepository;
//    private final WalletRepository walletRepository;
//    private final WalletTransactionRepository walletTransactionRepository;
//    private final PricingService pricingService;
//
//    public BookingServiceImpl(UserRepository userRepository,
//                              SeatRepository seatRepository,
//                              BookingRepository bookingRepository,
//                              WalletRepository walletRepository,
//                              WalletTransactionRepository walletTransactionRepository,
//                              PricingService pricingService) {
//        this.userRepository = userRepository;
//        this.seatRepository = seatRepository;
//        this.bookingRepository = bookingRepository;
//        this.walletRepository = walletRepository;
//        this.walletTransactionRepository = walletTransactionRepository;
//        this.pricingService = pricingService;
//    }
//
//    @Override
//    @Transactional
//    public BookingResponse createBooking(CreateBookingRequest request) {
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        Seat seat = seatRepository.findById(request.getSeatId())
//                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
//
//        Instant start = request.getStartTime();
//        Instant end = request.getEndTime();
//
//        List<Booking> overlaps = bookingRepository
//                .findBySeatAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
//                        seat, end, start
//                );
//        if (!overlaps.isEmpty()) {
//            throw new IllegalStateException("Seat already booked for this time range");
//        }
//
//        BigDecimal price = pricingService.calculatePrice(
//                seat.getStation().getId(),
//                seat.getId(),
//                start,
//                end
//        );
//
//        Wallet wallet = user.getWallet();
//        if (wallet.getBalance().compareTo(price) < 0) {
//            throw new IllegalStateException("Insufficient wallet balance");
//        }
//
//        wallet.setBalance(wallet.getBalance().subtract(price));
//        walletRepository.save(wallet);
//
//        WalletTransaction tx = new WalletTransaction();
//        tx.setUserId(user.getId());
//        tx.setType(Type.DEBIT);
//        tx.setAmount(price);
//        tx.setReference("BOOKING");
//        walletTransactionRepository.save(tx);
//
//        Booking booking = new Booking();
//        booking.setUserId(user.getId());
//        booking.setSeat(seat);
//        booking.setStartTime(start);
//        booking.setEndTime(end);
//        booking.setPrice(price);
//        booking.setStatus(Booking.Status.CONFIRMED);
//        booking = bookingRepository.save(booking);
//
//        BookingResponse response = new BookingResponse();
//        response.setBookingId(booking.getId());
//        response.setUserId(user.getId());
//        response.setSeatId(seat.getId());
//        response.setStartTime(start);
//        response.setEndTime(end);
//        response.setStatus(booking.getStatus().name());
//        response.setPrice(price);
//
//        return response;
//    }
//}
