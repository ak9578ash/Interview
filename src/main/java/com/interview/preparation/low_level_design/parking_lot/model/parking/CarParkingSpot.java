package com.interview.preparation.low_level_design.parking_lot.model.parking;

public class CarParkingSpot extends ParkingSpot{
    public CarParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.CAR);
    }
}
