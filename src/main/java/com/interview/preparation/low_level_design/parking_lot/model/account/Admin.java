package com.interview.preparation.low_level_design.parking_lot.model.account;

import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingFloorException;
import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingLotException;
import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingSpotException;
import com.interview.preparation.low_level_design.parking_lot.model.parking.*;
import com.interview.preparation.low_level_design.parking_lot.service.ParkingLotService;

public class Admin extends Account {
    private final ParkingLotService parkingLotService;

    public Admin(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        return parkingLotService.addParkingLot(parkingLot);
    }
    public ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor) throws InvalidParkingFloorException, InvalidParkingLotException {
        return parkingLotService.addParkingFloor(parkingLotId, parkingFloor);
    }

    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException, InvalidParkingLotException, InvalidParkingSpotException {
        return parkingLotService.addParkingSpot(parkingLotId, parkingFloorId, parkingSpot);
    }
    public EntranceGate addEntranceGate(String parkingLotId, EntranceGate entranceGate) throws InvalidParkingLotException {
        return parkingLotService.addEntryGate(parkingLotId, entranceGate);
    }
    public ExitGate addExitGate(String parkingLotId, ExitGate exitGate) throws InvalidParkingLotException {
        return parkingLotService.addExitGate(parkingLotId, exitGate);
    }

}
