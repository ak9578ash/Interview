package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Seat {
    private final String id;
    private final String rowId;
    private final String seatNo;
    private final Double price;
    private final SeatType seatType;

    public Seat(String rowId , String seatNo, Double price, SeatType seatType) {
        this.id = UUID.randomUUID().toString();
        this.rowId = rowId;
        this.seatNo = seatNo;
        this.price = price;
        this.seatType = seatType;
    }
}
