package com.interview.preparation.low_level_design.parking_lot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ParkingTicket {
    private String ticketNumber;
    private String licensePlateNumber;
    private String allocatedSpotId;
    private LocalDateTime issuedAt;
    private LocalDateTime vacatedAt;
    private double charges;
    private TicketStatus ticketStatus;
}
