package com.interview.preparation.low_level_design.cab_booking.service;

import com.interview.preparation.low_level_design.cab_booking.exception.BadRequestException;
import com.interview.preparation.low_level_design.cab_booking.exception.CabTemporarilyUnavailable;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;
import com.interview.preparation.low_level_design.cab_booking.model.Trip;
import com.interview.preparation.low_level_design.cab_booking.model.TripStatus;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import com.interview.preparation.low_level_design.cab_booking.repository.TripRepository;
import com.interview.preparation.low_level_design.cab_booking.strategies.CabMatchingStrategy;
import com.interview.preparation.low_level_design.cab_booking.strategies.PriceStrategy;
import com.interview.preparation.low_level_design.cab_booking.utils.CabLockProvider;

import java.util.List;
import java.util.stream.Collectors;

public class TripService {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
    private final TripRepository tripRepository;
    private final CabLockProvider cabLockProvider;
    private final CabMatchingStrategy cabMatchingStrategy;
    private final PriceStrategy priceStrategy;

    public TripService(TripRepository tripRepository, CabLockProvider cabLockProvider, CabMatchingStrategy cabMatchingStrategy, PriceStrategy priceStrategy) {
        this.tripRepository = tripRepository;
        this.cabLockProvider = cabLockProvider;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.priceStrategy = priceStrategy;
    }

    public Trip addTrip(User user, List<Cab> cabs, Location fromLocation, Location toLocation) throws CabTemporarilyUnavailable {
        if (isAnyCabAlreadyBooked(cabs)) {
            throw new CabTemporarilyUnavailable("cab is not available");
        }
        cabLockProvider.lockCabs(cabs, user.getId());
        Cab cab = cabMatchingStrategy.matchCabToRider(cabs, toLocation, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        Double charges = priceStrategy.getPrice(fromLocation, toLocation);

        Trip trip = new Trip(user, cab, charges, fromLocation, toLocation);

        return tripRepository.addTrip(trip);
    }

    private Boolean isAnyCabAlreadyBooked(List<Cab> cabs) {
        List<Cab> allBookedCabs = getAllBookedCab();
        for (Cab cab : cabs) {
            if (allBookedCabs.contains(cab)) {
                return true;
            }
        }
        return false;
    }

    public List<Cab> getAllBookedCab() {
        return tripRepository.getAllTrips()
                .stream()
                .filter(trip -> trip.getTripStatus() == TripStatus.CONFIRM)
                .map(Trip::getCab)
                .collect(Collectors.toList());
    }

    public List<Trip>getUserTrips(User user){
        return tripRepository.getAllUserTrips(user);
    }

    public void confirmTrip(Trip trip , User user) throws BadRequestException {
        if(!trip.getUser().equals(user)){
            throw new BadRequestException();
        }
        trip.confirmTrip();
    }

    public void endTrip(Trip trip,User user) throws BadRequestException {
        if(!trip.getUser().equals(user)){
            throw new BadRequestException();
        }
        trip.endTrip();
    }
}
