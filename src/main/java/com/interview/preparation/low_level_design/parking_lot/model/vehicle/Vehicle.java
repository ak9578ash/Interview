package com.interview.preparation.low_level_design.parking_lot.model.vehicle;

import com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;
    private ParkingTicket ticket;

    public Vehicle(String licenseNumber, VehicleType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }
}
