package com.interview.preparation.low_level_design.chess_game.model;

import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Piece {
  private final String name;
  private final PieceType pieceType;
  private final ColorType colorType;
  private boolean isKilled;
  private final MoveStrategy moveStrategy;

  public abstract boolean isValidMove(Box startBox, Box endbox );
}
