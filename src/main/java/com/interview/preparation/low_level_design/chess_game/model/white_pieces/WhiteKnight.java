package com.interview.preparation.low_level_design.chess_game.model.white_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class WhiteKnight extends Piece {
  public WhiteKnight(MoveStrategy knightMoveStrategy) {
    super("WN", PieceType.KNIGHT, ColorType.WHITE, false, knightMoveStrategy);
  }

  public boolean isValidMove(Box startBox, Box endbox) {
    return true;
  }
}
