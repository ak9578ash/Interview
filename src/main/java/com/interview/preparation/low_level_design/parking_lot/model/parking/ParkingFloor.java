package com.interview.preparation.low_level_design.parking_lot.model.parking;

import com.interview.preparation.low_level_design.parking_lot.exception.NoParkingSpotAvailableException;
import com.interview.preparation.low_level_design.parking_lot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.interview.preparation.low_level_design.parking_lot.model.parking.ParkingSpotType.*;

@Getter
@Setter
public class ParkingFloor {
    private String parkingFloorId;
    private Map<ParkingSpotType , Deque<ParkingSpot>> parkingSpots = new HashMap<>();
    private Map<String , ParkingSpot>usedParkingSpot = new HashMap<>();

    public ParkingFloor(String parkingFloorId){
        this.parkingFloorId = parkingFloorId;
        parkingSpots.put(ABLED, new ConcurrentLinkedDeque<>());
        parkingSpots.put(CAR, new  ConcurrentLinkedDeque<>());
        parkingSpots.put(LARGE,new ConcurrentLinkedDeque<>());
        parkingSpots.put(MOTORBIKE, new ConcurrentLinkedDeque<>());
        parkingSpots.put(ELECTRIC_BIKE, new ConcurrentLinkedDeque<>());
    }

    public boolean isFloorFull(){
        for (Map.Entry<ParkingSpotType, Deque<ParkingSpot>> entry : parkingSpots.entrySet()) {
            if(entry.getValue().size() > 0){
                return false;
            }
        }
        return true;
    }

    public static ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return CAR;
            case ELECTRIC:
                return ELECTRIC_BIKE;
            case MOTORBIKE:
                return MOTORBIKE;
            default:
                return LARGE;
        }
    }

    public boolean canPark(VehicleType vehicleType) {
        return canPark(getSpotTypeForVehicle(vehicleType));
    }

    public boolean canPark(ParkingSpotType parkingSpotType){
        return parkingSpots.get(parkingSpotType).size() > 0;
    }

    public ParkingSpot getSpot(VehicleType vehicleType) throws NoParkingSpotAvailableException {
        if(!canPark(vehicleType)){
            return null;
        }

        ParkingSpotType parkingSpotType = getSpotTypeForVehicle(vehicleType);
        ParkingSpot parkingSpot  =  parkingSpots.get(parkingSpotType).poll();

        usedParkingSpot.put(parkingSpot.getParkingSpotId() , parkingSpot);
        return parkingSpot;
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotId){
        ParkingSpot parkingSpot = usedParkingSpot.get(parkingSpotId);
        if(parkingSpot!=null){
            parkingSpot.freeSpot();
            parkingSpots.get(parkingSpot.getParkingSpotType())
                    .addFirst(parkingSpot);
            return parkingSpot;
        }
        return null;
    }


}
