package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Movie {
    private String id;
    private String name;

    public Movie(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
