package com.interview.preparation.low_level_design.parking_lot.repository;

import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingFloorException;
import com.interview.preparation.low_level_design.parking_lot.exception.InvalidParkingLotException;
import com.interview.preparation.low_level_design.parking_lot.model.parking.*;

import java.util.*;

public class ParkingLotRepository {
    public static Map<String, ParkingLot> parkingLotMap = new HashMap<>();
    public static List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.putIfAbsent(parkingLot.getParkingLotId(), parkingLot);
        parkingLots.add(parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLotMap.get(parkingLotId);
    }

    public ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor) throws InvalidParkingLotException, InvalidParkingFloorException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null) {
            throw new InvalidParkingLotException("Invalid parking lot");
        }
        Optional<ParkingFloor> floor = parkingLot.getParkingFloors()
                .stream()
                .filter(pFloor -> pFloor.getParkingFloorId().equalsIgnoreCase(parkingFloor.getParkingFloorId()))
                .findFirst();
        if (floor.isPresent()) {
            throw new InvalidParkingFloorException("Invalid parking floor");
        }
        parkingLotMap.get(parkingLotId).getParkingFloors().add(parkingFloor);
        return parkingFloor;
    }

    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingLotException, InvalidParkingFloorException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null) {
            throw new InvalidParkingLotException("Invalid parking lot");
        }
        Optional<ParkingFloor> floor = parkingLot.getParkingFloors()
                .stream()
                .filter(pFloor -> pFloor.getParkingFloorId().equalsIgnoreCase(parkingFloorId))
                .findFirst();
        if (!floor.isPresent()) {
            throw new InvalidParkingFloorException("Invalid parking floor");
        }
        Optional<ParkingSpot> spot = floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                .stream()
                .filter(pSpot -> pSpot.getParkingSpotId().equalsIgnoreCase(parkingSpot.getParkingSpotId()))
                .findFirst();
        if (spot.isPresent()) {
            return spot.get();
        }

        floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType()).add(parkingSpot);
        return parkingSpot;
    }

    public EntranceGate addEntryGate(String parkingLotId, EntranceGate entranceGate) throws InvalidParkingLotException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null) {
            throw new InvalidParkingLotException("Invalid parking lot");
        }
        Optional<EntranceGate> eGate = parkingLotMap.get(parkingLotId).getEntranceGates()
                .stream()
                .filter(ep -> ep.getId().equalsIgnoreCase(entranceGate.getId()))
                .findFirst();
        if (eGate.isPresent()) {
            return entranceGate;
        }
        parkingLotMap.get(parkingLotId).getEntranceGates().add(entranceGate);
        return entranceGate;
    }

    public ExitGate addExitGate(String parkingLotId, ExitGate exitGate) throws InvalidParkingLotException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null) {
            throw new InvalidParkingLotException("Invalid parking lot");
        }
        Optional<ExitGate> eGate = parkingLotMap.get(parkingLotId).getExitGates()
                .stream()
                .filter(ep -> ep.getId().equalsIgnoreCase(exitGate.getId()))
                .findFirst();
        if (eGate.isPresent()) {
            return exitGate;
        }
        parkingLotMap.get(parkingLotId).getExitGates().add(exitGate);
        return exitGate;
    }


}
