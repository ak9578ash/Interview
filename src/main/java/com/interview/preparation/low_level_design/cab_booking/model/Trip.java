package com.interview.preparation.low_level_design.cab_booking.model;

import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Trip {
    private String id;
    private User user;
    private List<Cab> cab;
    private TripStatus tripStatus;
    private Double charges;
    private Location fromLocation;
    private Location toLocation;

    public Trip(User user, List<Cab> cab, Double charges, Location fromLocation, Location toLocation) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.cab = cab;
        this.tripStatus = TripStatus.CREATED;
        this.charges = charges;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public void confirmTrip(){
        this.tripStatus = TripStatus.CONFIRM;
    }
    public void endTrip(){
        this.tripStatus = TripStatus.COMPLETED;
    }
}
