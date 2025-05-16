package com.interview.preparation.low_level_design.chess_game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerMove {
  private final int row;
  private final int col;
}
