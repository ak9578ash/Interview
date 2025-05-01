package com.interview.preparation.low_level_design.tic_tac_toe.model.win_strategy;

import com.interview.preparation.low_level_design.tic_tac_toe.model.Board;
import com.interview.preparation.low_level_design.tic_tac_toe.model.PieceType;

public class DefaultWinStrategy implements WinStrategy {
  @Override
  public boolean isWiningMove(Board board, int row, int col, PieceType pieceType) {
    String symbol = pieceType == PieceType.CROSS ? "X" : "O";

    // Check row
    if (board.getBoard().get(row).stream().allMatch(cell -> cell.equals(symbol))) {
      return true;
    }

    // Check column
    if (board.getBoard().stream().allMatch(r -> r.get(col).equals(symbol))) {
      return true;
    }

    // Check diagonal
    if (row == col && board.getBoard().stream().allMatch(r -> r.get(board.getBoard().indexOf(r)).equals(symbol))) {
      return true;
    }

    // Check anti-diagonal
    if (row + col == board.getSize() - 1 && board.getBoard().stream()
        .allMatch(r -> r.get(board.getSize() - 1 - board.getBoard().indexOf(r)).equals(symbol))) {
      return true;
    }

    return false;
  }
}
