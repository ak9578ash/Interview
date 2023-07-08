package com.interview.preparation.low_level_design.parking_lot.model.parking;

import com.interview.preparation.low_level_design.parking_lot.exception.NoParkingSpotAvailableException;
import com.interview.preparation.low_level_design.parking_lot.model.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EntranceGate {
    private String id;

    public EntranceGate(String id) {
        this.id = id;
    }

    public ParkingTicket getParkingTicket(ParkingLot parkingLot, Vehicle vehicle) throws NoParkingSpotAvailableException {
        if (!parkingLot.canPark(vehicle.getType())) {
            throw new NoParkingSpotAvailableException("Ticket is not generated because parking spot is not available");
        }
        ParkingSpot parkingSpot = parkingLot.getParkingSpot(vehicle.getType());
        if (parkingSpot == null) {
            return null;
        }
        return buildTicket(vehicle.getLicenseNumber(), parkingSpot.getParkingSpotId());
    }

    private ParkingTicket buildTicket(String vehicleLicenseNumber, String parkingSpotId) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setAllocatedSpotId(parkingSpotId);
        parkingTicket.setLicensePlateNumber(vehicleLicenseNumber);
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }

}
