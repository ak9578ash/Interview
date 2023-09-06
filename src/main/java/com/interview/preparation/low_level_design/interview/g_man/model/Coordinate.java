package com.interview.preparation.low_level_design.interview.g_man.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private int  x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
