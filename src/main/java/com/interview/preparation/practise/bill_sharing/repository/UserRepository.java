package com.interview.preparation.practise.bill_sharing.repository;

import com.interview.preparation.practise.bill_sharing.exception.UserNotFoundException;
import com.interview.preparation.practise.bill_sharing.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String , User> userMap = new HashMap<>();
    List<User> userList = new ArrayList<>();

    public User addUser(User user){
        userMap.putIfAbsent(user.getId() , user);
        userList.add(user);
        return user;
    }

    public User getUserById(String userId) throws UserNotFoundException {
        if(!userMap.containsKey(userId)){
            throw new UserNotFoundException("provided user not found");
        }
        return userMap.get(userId);
    }
}
