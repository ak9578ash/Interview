package com.interview.preparation.low_level_design.cab_booking.service;

import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.utils.CabLockProvider;

import java.util.ArrayList;
import java.util.List;

public class CabAvailabilityService {
    private final TripService tripService;
    private final CabLockProvider cabLockProvider;
    private final CabService cabService;

    public CabAvailabilityService(TripService tripService, CabLockProvider cabLockProvider, CabService cabService) {
        this.tripService = tripService;
        this.cabLockProvider = cabLockProvider;
        this.cabService = cabService;
    }

    public List<Cab> getUnavailableCabs() {
        List<Cab> bookedCabs = tripService.getAllBookedCab();
        List<Cab> lockedCabs = cabLockProvider.getLockedCabs();
        List<Cab> unavailableCabs = new ArrayList<>();

        unavailableCabs.addAll(bookedCabs);
        unavailableCabs.addAll(lockedCabs);
        return unavailableCabs;
    }

    public List<Cab> getAvailableCabs() {
        List<Cab> allCabs = cabService.getAllCabs();
        List<Cab> unavailableCabs = getUnavailableCabs();

        List<Cab> availableCabs = new ArrayList<>(allCabs);
        availableCabs.removeAll(unavailableCabs);

        return availableCabs;
    }

}
