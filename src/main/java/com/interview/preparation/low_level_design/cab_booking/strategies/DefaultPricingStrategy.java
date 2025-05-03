package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;
import java.util.List;

public class DefaultPricingStrategy implements PriceStrategy{
    public static final Double PER_KM_RATE = 10.0;
    @Override
    public Double getPrice(Location fromLocation, Location toLocation, List<Cab> cabs) {
        return fromLocation.getDistance(toLocation) * PER_KM_RATE * cabs.size();
    }
}
