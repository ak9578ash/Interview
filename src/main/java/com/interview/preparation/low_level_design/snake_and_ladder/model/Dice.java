package com.interview.preparation.low_level_design.snake_and_ladder.model;

import com.interview.preparation.low_level_design.snake_and_ladder.model.dice_roll_strategy.RollStrategy;
import org.apache.commons.lang3.RandomUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Dice {
    private int minVal;
    private int maxVal;
    private int currentVal;
    private final RollStrategy rollStrategy;

    public int roll(){
        return rollStrategy.roll(minVal, maxVal);
    }
}
