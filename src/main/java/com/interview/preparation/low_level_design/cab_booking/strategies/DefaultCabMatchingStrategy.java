package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;

import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy{
    @Override
    public Cab matchCabToRider(List<Cab> candidateCabs, Location toPoint, Double allowedDistance) {
        for(Cab cab :candidateCabs){
            if(cab.getCurrentLocation().getDistance(toPoint) <= allowedDistance){
                return cab;
            }
        }
        return null;
    }
}
