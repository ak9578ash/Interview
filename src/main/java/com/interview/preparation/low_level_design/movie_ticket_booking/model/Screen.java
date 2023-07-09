package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Screen {
    private String id;
    private String name;
    private List<Seat> seats;

    public Screen(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.seats = new ArrayList<>();
    }

    public Seat addSeat(Seat seat){
        this.seats.add(seat);
        return seat;
    }

}
