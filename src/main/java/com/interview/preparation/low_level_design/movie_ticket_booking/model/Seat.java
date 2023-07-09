package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Seat {
    private String id;
    private String rowId;
    private String seatNo;

    public Seat(String rowId , String seatNo){
        this.id = UUID.randomUUID().toString();
        this.rowId = rowId;
        this.seatNo = seatNo;
    }

}
