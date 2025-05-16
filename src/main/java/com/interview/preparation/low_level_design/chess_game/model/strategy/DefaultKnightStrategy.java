package com.interview.preparation.low_level_design.chess_game.model.strategy;

import com.interview.preparation.low_level_design.chess_game.model.Box;

public class DefaultKnightStrategy implements MoveStrategy {

  @Override
  public boolean isValidMove(Box startBox, Box endbox) {
    return false;
  }
}
