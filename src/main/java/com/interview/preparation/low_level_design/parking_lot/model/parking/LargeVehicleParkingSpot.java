package com.interview.preparation.low_level_design.parking_lot.model.parking;

public class LargeVehicleParkingSpot extends ParkingSpot{
    public LargeVehicleParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.LARGE);
    }
}
