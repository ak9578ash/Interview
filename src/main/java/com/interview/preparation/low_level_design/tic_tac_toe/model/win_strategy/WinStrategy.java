package com.interview.preparation.low_level_design.tic_tac_toe.model.win_strategy;

import com.interview.preparation.low_level_design.tic_tac_toe.model.Board;
import com.interview.preparation.low_level_design.tic_tac_toe.model.PieceType;

public interface WinStrategy {
  boolean isWiningMove(Board board, int row, int col, PieceType pieceType);
}
