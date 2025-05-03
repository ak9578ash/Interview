package com.interview.preparation.low_level_design.cab_booking.strategies;

import com.interview.preparation.low_level_design.cab_booking.model.Cab;
import com.interview.preparation.low_level_design.cab_booking.model.Location;
import java.util.List;

public interface PriceStrategy {
    Double getPrice(Location fromLocation , Location toLocation, List<Cab> cabs);
}
