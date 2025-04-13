package com.interview.preparation.low_level_design.parking_lot.model.strategy;

import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingSpotType;
import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingTicket;

public interface CostStrategy {
  double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType);
}
