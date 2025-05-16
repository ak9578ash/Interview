package com.interview.preparation.low_level_design.chess_game.model.black_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class BlackRook extends Piece {
  public BlackRook(MoveStrategy rookMoveStrategy) {
    super("BR", PieceType.ROOK, ColorType.BLACK, false, rookMoveStrategy);
  }

  public boolean isValidMove(Box startBox, Box endbox) {
    return true;
  }

}
