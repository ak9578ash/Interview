package com.interview.preparation.low_level_design.bill_sharing.repository;

import com.interview.preparation.low_level_design.bill_sharing.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserRepository {
    public static Map<String, User> userHashMap = new HashMap<>();
    public static List<User> usersList = new ArrayList<>();

    public User addUser(User user) {
        userHashMap.putIfAbsent(user.getUserId(), user);
        usersList.add(user);
        return user;
    }
}
