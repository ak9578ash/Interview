package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;

import java.util.List;

public interface CabMatchingStrategy {
    Cab matchCabToRider(List<Cab> candidateCabs, Location toPoint , Double allowedDistance);
}
