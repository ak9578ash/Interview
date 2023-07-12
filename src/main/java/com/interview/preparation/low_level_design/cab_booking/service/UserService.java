package com.interview.preparation.low_level_design.cab_booking.service;

import com.interview.preparation.low_level_design.cab_booking.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import com.interview.preparation.low_level_design.cab_booking.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.addUser(user);
    }

    public User getUserById(String userId) throws UserNotFoundException {
        return userRepository.getUserById(userId);
    }
}
