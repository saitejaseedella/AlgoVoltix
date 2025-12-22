//package com.algovoltix.evbooking.service.impl;
//
//import com.algovoltix.evbooking.entity.User;
//import com.algovoltix.evbooking.repository.UserRepository;
//import com.algovoltix.evbooking.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User getUserById(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.orElse(null);
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User updateUser(Long id, User user) {
//        if (userRepository.existsById(id)) {
////            user.setId(id);
//            return userRepository.save(user);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//}
