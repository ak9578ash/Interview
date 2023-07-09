package com.interview.preparation.low_level_design.movie_ticket_booking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Theatre {
    private String id;
    private String name;
    private Address address;
    private List<Screen> screens;

    public Theatre(String name , Address address){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.screens = new ArrayList<>();
    }

   public Screen addScreen(Screen screen){
        this.screens.add(screen);
        return screen;
   }
}
