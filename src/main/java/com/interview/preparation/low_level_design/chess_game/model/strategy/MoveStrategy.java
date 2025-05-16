package com.interview.preparation.low_level_design.chess_game.model.strategy;

import com.interview.preparation.low_level_design.chess_game.model.Box;

public interface MoveStrategy {
  boolean isValidMove(Box startBox, Box endbox);
}
