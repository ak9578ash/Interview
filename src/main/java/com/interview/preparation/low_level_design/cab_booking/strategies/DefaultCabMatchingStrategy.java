package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;

import java.util.ArrayList;
import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy{
    @Override
    public List<Cab> matchCabToRider(List<Cab> candidateCabs, Location toPoint, Double allowedDistance) {
        List<Cab>matchedCabs = new ArrayList<>();
        for(Cab cab :candidateCabs){
            if(cab.getCurrentLocation().getDistance(toPoint) <= allowedDistance){
                 matchedCabs.add(cab);
            }
        }
        return matchedCabs;
    }
}
