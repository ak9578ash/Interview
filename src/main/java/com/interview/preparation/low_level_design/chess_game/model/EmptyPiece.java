package com.interview.preparation.low_level_design.chess_game.model;

public class EmptyPiece extends  Piece{
  public EmptyPiece() {
    super("EP", PieceType.EMPTY, ColorType.EMPTY, false, null);
  }

  public boolean isValidMove(Box startBox, Box endbox) {
    return true;
  }
}
