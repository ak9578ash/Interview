package com.interview.preparation.low_level_design.cab_booking.model.account;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private String id;
    private UserProfile userProfile;
    private Address address;

    public User(UserProfile userProfile , Address address){
        this.id = UUID.randomUUID().toString();
        this.userProfile = userProfile;
        this.address = address;
    }
}
