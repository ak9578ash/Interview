package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Location;

public class DefaultPricingStrategy implements PriceStrategy{
    public static final Double PER_KM_RATE = 10.0;
    @Override
    public Double getPrice(Location fromLocation, Location toLocation) {
        return fromLocation.getDistance(toLocation) * PER_KM_RATE ;
    }
}
