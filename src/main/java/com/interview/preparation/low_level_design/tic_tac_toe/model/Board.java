package com.interview.preparation.low_level_design.tic_tac_toe.model;

import com.interview.preparation.low_level_design.tic_tac_toe.model.win_strategy.DefaultWinStrategy;
import com.interview.preparation.low_level_design.tic_tac_toe.model.win_strategy.WinStrategy;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Board {
  private final int size;
  private final List<List<String>> board;
  private final WinStrategy winStrategy;
  public Board(int size) {
    this.size = size;
    this.board = new ArrayList<>();
    this.winStrategy =  new DefaultWinStrategy();
    createBoard();
  }

  public Board(int size, WinStrategy winStrategy) {
    this.size = size;
    this.board = new ArrayList<>();
    this.winStrategy =  winStrategy;
    createBoard();
  }

  private void createBoard() {
    for (int i = 0; i < size; i++) {
      List<String> row = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        row.add("_");
      }
      board.add(row);
    }
  }

  public void printBoard() {
    for (int i = 0; i < board.size(); i++) {
      for (int j = 0; j < board.get(i).size(); j++) {
        System.out.print(board.get(i).get(j));
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  public boolean isValidMove(int row, int col, PieceType pieceType) {
    if (row < 0 || row >= size || col < 0 || col >= size) {
      return false;
    }

    if (!board.get(row).get(col).equals("_")) {
      return false;
    }

    if (pieceType == PieceType.CROSS && board.get(row).get(col).equals("X")) {
      return false;
    }

    if (pieceType == PieceType.CIRCLE && board.get(row).get(col).equals("O")) {
      return false;
    }

    return true;
  }

  public void updateBoard(int row, int col, PieceType pieceType) {
    if (pieceType == PieceType.CROSS) {
      board.get(row).set(col, "X");
    } else if (pieceType == PieceType.CIRCLE) {
      board.get(row).set(col, "O");
    }
  }

  public boolean isWinningMove(int row, int col, PieceType pieceType) {
    return winStrategy.isWiningMove(this, row, col, pieceType);
  }

  public boolean isBoardFull() {
    for (List<String> row : board) {
      for (String cell : row) {
        if (cell.equals("_")) {
          return false;
        }
      }
    }
    return true;
  }
}
