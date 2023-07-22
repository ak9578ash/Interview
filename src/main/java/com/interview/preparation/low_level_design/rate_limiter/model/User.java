package com.interview.preparation.low_level_design.rate_limiter.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private String id;
    private UserProfile userProfile;

    public User(UserProfile userProfile){
        this.id = UUID.randomUUID().toString();
        this.userProfile = userProfile;
    }
}
