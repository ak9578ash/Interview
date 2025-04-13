package com.interview.preparation.low_level_design.parking_lot.model.strategy;

import com.interview.preparation.low_level_design.parking_lot.model.parking.HourlyCost;
import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingSpotType;
import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingTicket;
import java.time.Duration;
import java.time.LocalDateTime;

public class DefaultCostStrategy implements CostStrategy {
  @Override
  public double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
    Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
    long hours = duration.toHours();
    if (hours == 0)
      hours = 1;
    return hours * new HourlyCost().getCost(parkingSpotType);
  }
}
