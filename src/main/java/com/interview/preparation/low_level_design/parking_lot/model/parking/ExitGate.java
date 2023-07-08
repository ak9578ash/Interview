package com.interview.preparation.low_level_design.parking_lot.model.parking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExitGate {
    private String id;

    public ParkingTicket scanAndVacate(ParkingLot parkingLot , ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot = parkingLot.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setCharges(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
        parkingTicket.setVacatedAt(LocalDateTime.now());
        return parkingTicket;
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        if (hours == 0)
            hours = 1;
        double amount = hours * new HourlyCost().getCost(parkingSpotType);
        return amount;
    }
}
