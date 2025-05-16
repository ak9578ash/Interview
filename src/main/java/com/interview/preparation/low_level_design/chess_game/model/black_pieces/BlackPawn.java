package com.interview.preparation.low_level_design.chess_game.model.black_pieces;

import com.interview.preparation.low_level_design.chess_game.model.Box;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Piece;
import com.interview.preparation.low_level_design.chess_game.model.PieceType;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;


public class BlackPawn extends Piece {
  public BlackPawn(MoveStrategy pawnMoveStrategy) {
    super("BP", PieceType.PAWN, ColorType.BLACK, false, pawnMoveStrategy);
  }

  public boolean isValidMove(Box startBox, Box endbox) {
    return super.getMoveStrategy().isValidMove(startBox, endbox);
  }
}
