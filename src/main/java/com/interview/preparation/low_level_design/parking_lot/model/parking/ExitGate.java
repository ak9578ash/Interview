package com.interview.preparation.low_level_design.parking_lot.model.parking;

import com.interview.preparation.low_level_design.parking_lot.model.strategy.CostStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExitGate {
    private String id;
    private final CostStrategy paymentStrategy;

    public ParkingTicket scanAndVacate(ParkingLot parkingLot , ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot = parkingLot.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setCharges(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
        parkingTicket.setVacatedAt(LocalDateTime.now());
        return parkingTicket;
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        return paymentStrategy.calculateCost(parkingTicket, parkingSpotType);
    }
}
