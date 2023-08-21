package com.interview.preparation.low_level_design.snake_and_ladder.model;

import org.apache.commons.lang3.RandomUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Dice {
    private int minVal;
    private int maxVal;
    private int currentVal;

    public int roll(){
        return RandomUtils.nextInt(minVal , maxVal+1);
    }
}
