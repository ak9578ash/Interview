package com.interview.preparation.low_level_design.parking_lot.model.parking;

public class MotorBikeParkingSpot extends ParkingSpot{
    public MotorBikeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.MOTORBIKE);
    }
}
