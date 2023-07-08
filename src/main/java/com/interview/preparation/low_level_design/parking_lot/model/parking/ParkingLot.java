package com.interview.preparation.low_level_design.parking_lot.model.parking;

import com.interview.preparation.low_level_design.parking_lot.exception.NoParkingSpotAvailableException;
import com.interview.preparation.low_level_design.parking_lot.model.account.Address;
import com.interview.preparation.low_level_design.parking_lot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingFloor.getSpotTypeForVehicle;

@Setter
@Getter
public class ParkingLot {
    private String parkingLotId;
    private Address address;
    private List<ParkingFloor> parkingFloors;
    private List<EntranceGate> entranceGates;
    private List<ExitGate> exitGates;


    public ParkingLot(Address address) {
        this.parkingLotId = UUID.randomUUID().toString();
        this.address = address;
        this.parkingFloors = new ArrayList<>();
        this.entranceGates = new ArrayList<>();
        this.exitGates = new ArrayList<>();
    }

    public boolean isFull() {
        for (ParkingFloor parkingFloor : this.parkingFloors) {
            if (!parkingFloor.isFloorFull()) {
                return false;
            }
        }
        return true;
    }

    public boolean canPark(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : this.parkingFloors) {
            if (parkingFloor.canPark(getSpotTypeForVehicle(vehicleType)))
                return true;
        }
        return false;
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType) throws NoParkingSpotAvailableException {
        for (ParkingFloor parkingFloor : this.parkingFloors) {
            ParkingSpot parkingSpot = parkingFloor.getSpot(vehicleType);
            if (parkingSpot != null) {
                return parkingSpot;
            }
        }
        throw new NoParkingSpotAvailableException(String.format("Parking Spot Not Available for %s vehicle type", vehicleType.toString()));
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotId) {
        for (ParkingFloor parkingFloor : this.parkingFloors) {
            ParkingSpot parkingSpot = parkingFloor.vacateParkingSpot(parkingSpotId);
            if (parkingSpot != null)
                return parkingSpot;
        }
        return null;
    }
}
