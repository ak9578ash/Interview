package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Location;

public interface PriceStrategy {
    Double getPrice(Location fromLocation , Location toLocation);
}
