package com.interview.preparation.low_level_design.cab_booking.model;

import com.interview.preparation.low_level_design.cab_booking.model.account.Address;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import com.interview.preparation.low_level_design.cab_booking.model.account.UserProfile;

public class Rider extends User {
    public Rider(UserProfile userProfile, Address address) {
        super(userProfile, address);
    }
}
