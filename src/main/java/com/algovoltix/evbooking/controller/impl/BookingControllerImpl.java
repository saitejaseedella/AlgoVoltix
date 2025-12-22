package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.BookingController;
import com.algovoltix.evbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingControllerImpl implements BookingController {

    @Autowired
    private BookingService bookingService;

}
