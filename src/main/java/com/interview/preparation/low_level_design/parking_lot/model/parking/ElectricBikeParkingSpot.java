package com.interview.preparation.low_level_design.parking_lot.model.parking;

public class ElectricBikeParkingSpot extends ParkingSpot{
    public ElectricBikeParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.ELECTRIC_BIKE);
    }
}
