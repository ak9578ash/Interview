package com.interview.preparation.low_level_design.parking_lot.model.vehicle;

public class Car extends Vehicle{
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}
