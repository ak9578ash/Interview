package com.interview.preparation.low_level_design.cab_booking.repository;

import com.interview.preparation.low_level_design.cab_booking.model.Trip;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRepository {
    private static final Map<String , Trip> tripMap  = new HashMap<>();
    private static final Map<String , List<Trip>> userTripMap = new HashMap<>();
    private static final List<Trip> tripList = new ArrayList<>();

    public Trip addTrip(Trip trip){
        tripMap.putIfAbsent(trip.getId() ,trip);
        if(!userTripMap.containsKey(trip.getUser().getId())){
            userTripMap.put(trip.getUser().getId(),new ArrayList<>());
        }
        userTripMap.get(trip.getUser().getId()).add(trip);
        tripList.add(trip);
        return trip;
    }
    public List<Trip> getAllTrips(){
        return tripList;
    }

    public List<Trip>getAllUserTrips(User user){
        return userTripMap.get(user.getId());
    }
}
