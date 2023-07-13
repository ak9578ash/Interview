package com.interview.preparation.low_level_design.snake_and_ladder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {
    private String name;
    @Setter private int position;
    @Setter private Boolean won;

    public Player(String name){
        this.name = name;
        this.position = 0;
        this.won = false;
    }
}
