package com.interview.preparation.low_level_design.cab_booking.model;

import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Getter
@Setter
public class Cab {
    private String id;
    private String licensePlate;
    private User driver;
    private Trip currentTrip;
    private Location currentLocation;
    private Boolean isAvailable;

    public Cab(String licensePlate ,User driver){
        this.id = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.driver = driver;
        this.isAvailable = true;
    }
}
