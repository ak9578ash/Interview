package com.interview.preparation.low_level_design.chess_game.model.black_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class BlackQueen extends Piece {
  public BlackQueen(MoveStrategy queenMoveStrategy) {
    super("BQ", PieceType.QUEEN, ColorType.BLACK, false, queenMoveStrategy);
  }

  @Override
  public boolean isValidMove(Box startBox, Box endbox) {
    // Implement the logic to check if the move is valid for a queen
    return true;
  }
}
