package com.interview.preparation.low_level_design.tic_tac_toe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {
  private final String name;
  private final PieceType pieceType;
}
