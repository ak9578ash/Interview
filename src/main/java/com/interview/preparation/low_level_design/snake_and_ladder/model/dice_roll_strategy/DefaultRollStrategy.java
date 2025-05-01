package com.interview.preparation.low_level_design.snake_and_ladder.model.dice_roll_strategy;

import org.apache.commons.lang3.RandomUtils;

public class DefaultRollStrategy implements RollStrategy{
  @Override
  public int roll(int minVal , int maxVal) {
    return RandomUtils.nextInt(minVal , maxVal+1);
  }
}
