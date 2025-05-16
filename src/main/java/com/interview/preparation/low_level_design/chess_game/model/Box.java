package com.interview.preparation.low_level_design.chess_game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Box {
  private Piece piece;
  private final int x;
  private final int y;
}
