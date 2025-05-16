package com.interview.preparation.low_level_design.chess_game.model.black_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class BlackKing extends Piece {

  public BlackKing(MoveStrategy kingMoveStrategy) {
    super("BK", PieceType.KING, ColorType.BLACK, false, kingMoveStrategy);
  }
  @Override
  public boolean isValidMove(Box startBox, Box endbox) {
    return true;
  }
}
