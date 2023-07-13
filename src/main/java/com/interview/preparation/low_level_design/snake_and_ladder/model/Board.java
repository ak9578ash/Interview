package com.interview.preparation.low_level_design.snake_and_ladder.model;

import lombok.Getter;

@Getter
public class Board {
    private int size;
    private int end;
    private int start;


    public Board(int size) {
        this.size = size;
        this.start = 1;
        this.end = this.start + size - 1;
    }
}
