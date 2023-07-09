package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import com.interview.preparation.low_level_design.movie_ticket_booking.model.account.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Payment {
    private String id;
    private Booking booking;
    private Double charges;

    public Payment(Booking booking , Double charges){
        this.id = UUID.randomUUID().toString();
        this.booking = booking;
        this.charges = charges;
    }
}
