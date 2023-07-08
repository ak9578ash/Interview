package com.interview.preparation.low_level_design.parking_lot.service;

import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingFloorException;
import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingLotException;
import com.interview.preparation.low_level_design.parking_lot.model.parking.*;
import com.interview.preparation.low_level_design.parking_lot.repository.ParkingLotRepository;

public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.addParkingLot(parkingLot);
    }

    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLotRepository.getParkingLot(parkingLotId);
    }

    public ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor) throws InvalidParkingFloorException, InvalidParkingLotException {
        return parkingLotRepository.addParkingFloor(parkingLotId, parkingFloor);
    }

    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException, InvalidParkingLotException {
        return parkingLotRepository.addParkingSpot(parkingLotId, parkingFloorId, parkingSpot);
    }

    public EntranceGate addEntryGate(String parkingLotId, EntranceGate entranceGate) throws InvalidParkingLotException {
        return parkingLotRepository.addEntryGate(parkingLotId, entranceGate);
    }

    public ExitGate addExitGate(String parkingLotId, ExitGate exitGate) throws InvalidParkingLotException {
        return parkingLotRepository.addExitGate(parkingLotId, exitGate);
    }


}
