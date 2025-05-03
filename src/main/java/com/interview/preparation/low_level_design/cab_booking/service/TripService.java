package com.interview.preparation.low_level_design.cab_booking.service;

import com.interview.preparation.low_level_design.cab_booking.exception.BadRequestException;
import com.interview.preparation.low_level_design.cab_booking.exception.CabTemporarilyUnavailable;
import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;
import com.interview.preparation.low_level_design.cab_booking.model.Payment;
import com.interview.preparation.low_level_design.cab_booking.model.Trip;
import com.interview.preparation.low_level_design.cab_booking.model.TripStatus;
import com.interview.preparation.low_level_design.cab_booking.model.account.User;
import com.interview.preparation.low_level_design.cab_booking.repository.TripRepository;
import com.interview.preparation.low_level_design.cab_booking.strategies.CabMatchingStrategy;
import com.interview.preparation.low_level_design.cab_booking.strategies.PriceStrategy;
import com.interview.preparation.low_level_design.cab_booking.utils.CabLockProvider;

import java.util.Collection;
import java.util.List;

public class TripService {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
    private final TripRepository tripRepository;
    private final CabLockProvider cabLockProvider;
    private final CabMatchingStrategy cabMatchingStrategy;
    private final PriceStrategy priceStrategy;
    private final PaymentService paymentService;

    public TripService(TripRepository tripRepository, CabLockProvider cabLockProvider,
                       CabMatchingStrategy cabMatchingStrategy, PriceStrategy priceStrategy,
                       PaymentService paymentService) {
        this.tripRepository = tripRepository;
        this.cabLockProvider = cabLockProvider;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.priceStrategy = priceStrategy;
        this.paymentService = paymentService;
    }

    public Trip addTrip(User user, List<Cab> cabs, Location fromLocation, Location toLocation) throws CabTemporarilyUnavailable {
        if (Boolean.TRUE.equals(isAnyCabAlreadyBooked(cabs))) {
            throw new CabTemporarilyUnavailable("selected cab is not available");
        }
        cabLockProvider.lockCabs(cabs, user.getId());
        List<Cab> cab = cabMatchingStrategy.matchCabToRider(cabs, toLocation, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        Double charges = priceStrategy.getPrice(fromLocation, toLocation) * cab.size();

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
                .flatMap(Collection::stream)
                .toList();
    }

    public List<Trip>getUserTrips(User user){
        return tripRepository.getAllUserTrips(user);
    }

    public void confirmTrip(Trip trip , User user) throws BadRequestException {
        if(!trip.getUser().equals(user)){
            throw new BadRequestException();
        }

        for(Cab cab : trip.getCab()){
            if(Boolean.FALSE.equals(cabLockProvider.validateLock(cab,user.getId()))){
                throw new BadRequestException("Cab is not locked by given user");
            }
        }

        Payment payment = paymentService.getPaymentByTripId(trip.getId());
        if (payment == null) {
            throw new BadRequestException("Payment is not completed by the user");
        }
        trip.confirmTrip();
    }

    public void endTrip(Trip trip,User user) throws BadRequestException {
        if(!trip.getUser().equals(user)){
            throw new BadRequestException();
        }
        trip.endTrip();
        cabLockProvider.unlockCabs(trip.getCab(),user.getId());
    }
}
