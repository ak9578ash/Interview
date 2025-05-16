package com.interview.preparation.low_level_design.chess_game.model.black_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class BlackKnight extends Piece {
  public BlackKnight(MoveStrategy knightMoveStrategy) {
    super("BN", PieceType.KNIGHT, ColorType.BLACK, false, knightMoveStrategy);
  }

  public boolean isValidMove(Box startBox, Box endbox) {
    return true;
  }
}
