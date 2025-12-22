package com.algovoltix.evbooking.controller.impl;

import com.algovoltix.evbooking.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

//    @Autowired
//    private UserService userService;

  @PostMapping("/testUser")
  public ResponseEntity<String> seyHello() {
    return ResponseEntity.ok("Hello from User Controller!");
  }

}
