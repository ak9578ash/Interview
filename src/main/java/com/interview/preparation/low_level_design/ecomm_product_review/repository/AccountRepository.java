package com.interview.preparation.low_level_design.ecomm_product_review.repository;

import com.interview.preparation.low_level_design.ecomm_product_review.exception.UserNotFoundException;
import com.interview.preparation.low_level_design.ecomm_product_review.model.account.User;
import com.interview.preparation.low_level_design.ecomm_product_review.model.review.Review;
import com.interview.preparation.low_level_design.parking_lot.model.account.Admin;

import java.util.*;

public class AccountRepository {
    public static List<User> users = new ArrayList<>();
    public static Map<String, User> usersMap = new HashMap<>();

    public User addUser(User user){
        usersMap.putIfAbsent(user.getUserId() , user);
        users.add(user);
        return user;
    }

    public User getUserByUserId(String userId) throws UserNotFoundException {
        User user = usersMap.get(userId);
        if(user==null){
            throw new UserNotFoundException(String.format("user not found with this %s userId",userId));
        }
        return user;
    }

    public User getUserByEmailId(String emailId){
        Optional<User> user = users
                .stream()
                .filter(u -> u.getUserProfile().getEmailId().equalsIgnoreCase(emailId))
                .findFirst();
        return user.orElse(null);
    }

}
