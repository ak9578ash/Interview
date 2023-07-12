package com.interview.preparation.low_level_design.cab_booking.repository;

import com.interview.preparation.low_level_design.cab_booking.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    public static Map<String , User> userMap = new HashMap<>();
    public static List<User> userList = new ArrayList<>();

    public User addUser(User user){
        userMap.putIfAbsent(user.getId() , user);
        userList.add(user);
        return user;
    }

    public User getUserById(String userId) throws UserNotFoundException {
        User user = userMap.get(userId);
        if(user==null){
            throw new UserNotFoundException("user does not exist");
        }
        return user;
    }

}
