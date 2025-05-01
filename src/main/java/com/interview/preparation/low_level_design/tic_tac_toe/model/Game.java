package com.interview.preparation.low_level_design.tic_tac_toe.model;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Game {
  private static Game instance = null;
  private final Board board;
  private final ArrayDeque<Player> players;
  private final Scanner scanner = new Scanner(System.in);

  private Game(int boardSize) {
    this.board = new Board(boardSize);
    this.players = new ArrayDeque<>(2);
  }

  public static synchronized Game getInstance() {
    if (instance == null) {
      instance = new Game(3);
    }
    return instance;
  }

  public void addPlayers(Player player) {
    this.players.add(player);
  }

  public void playGame() {
    while (true) {
      System.out.println("Current Board State");
      board.printBoard();
      System.out.println("--------------------");

      Player currentPlayer = players.poll();
      System.out.println(currentPlayer.getName() + "'s turn. Enter row and column (0-indexed):");

      int row = scanner.nextInt();
      int col = scanner.nextInt();

      if (!board.isValidMove(row, col, currentPlayer.getPieceType())) {
        System.out.println("Invalid move. Try again.");
        players.addFirst(currentPlayer);
        continue;
      }

      board.updateBoard(row, col, currentPlayer.getPieceType());

      if (board.isWinningMove(row, col, currentPlayer.getPieceType())) {
        System.out.println(currentPlayer.getName() + " wins!");
        break;
      }

      if (board.isBoardFull()) {
        System.out.println("It's a draw!");
        break;
      }
      players.add(currentPlayer);
    }
  }
}
