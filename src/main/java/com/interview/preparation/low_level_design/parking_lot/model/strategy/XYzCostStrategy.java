package com.interview.preparation.low_level_design.parking_lot.model.strategy;

import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingSpotType;
import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingTicket;

public class XYzCostStrategy implements CostStrategy{
  @Override
  public double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
    // Implement the cost calculation logic for XYzCostStrategy
    // For example, you can use a different formula or rate based on the parking spot type
    double baseRate = 10.0; // Example base rate
    double cost = Math.random(); // Example calculation
    return cost;
  }
}
