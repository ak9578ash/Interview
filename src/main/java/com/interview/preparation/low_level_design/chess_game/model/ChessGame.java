package com.interview.preparation.low_level_design.chess_game.model;

import com.interview.preparation.low_level_design.chess_game.model.black_pieces.BlackBishop;
import com.interview.preparation.low_level_design.chess_game.model.black_pieces.BlackKing;
import com.interview.preparation.low_level_design.chess_game.model.black_pieces.BlackKnight;
import com.interview.preparation.low_level_design.chess_game.model.black_pieces.BlackPawn;
import com.interview.preparation.low_level_design.chess_game.model.black_pieces.BlackQueen;
import com.interview.preparation.low_level_design.chess_game.model.black_pieces.BlackRook;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;
import com.interview.preparation.low_level_design.chess_game.model.white_pieces.WhiteBishop;
import com.interview.preparation.low_level_design.chess_game.model.white_pieces.WhiteKing;
import com.interview.preparation.low_level_design.chess_game.model.white_pieces.WhiteKnight;
import com.interview.preparation.low_level_design.chess_game.model.white_pieces.WhitePawn;
import com.interview.preparation.low_level_design.chess_game.model.white_pieces.WhiteQueen;
import com.interview.preparation.low_level_design.chess_game.model.white_pieces.WhiteRook;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChessGame {
  private static ChessGame instance = null;
  private final List<List<Box>> board;
  private final ArrayDeque<Player> players;
  private final MoveStrategy pawnMoveStrategy;
  private final MoveStrategy knightMoveStrategy;
  private final MoveStrategy bishopMoveStrategy;
  private final MoveStrategy kingMoveStrategy;
  private final MoveStrategy queenMoveStrategy;
  private final MoveStrategy rookMoveStrategy;
  private final Scanner scanner;
  private GameStatus gameStatus;

  private ChessGame(MoveStrategy pawnMoveStrategy, MoveStrategy knightMoveStrategy, MoveStrategy bishopMoveStrategy,
                    MoveStrategy kingMoveStrategy, MoveStrategy queenMoveStrategy, MoveStrategy rookMoveStrategy) {
    this.pawnMoveStrategy = pawnMoveStrategy;
    this.knightMoveStrategy = knightMoveStrategy;
    this.bishopMoveStrategy = bishopMoveStrategy;
    this.kingMoveStrategy = kingMoveStrategy;
    this.queenMoveStrategy = queenMoveStrategy;
    this.rookMoveStrategy = rookMoveStrategy;
    this.board = createBoard();
    this.players = new ArrayDeque<>();
    this.scanner = new Scanner(System.in);
    this.gameStatus = GameStatus.IN_PROGRESS;

  }

  public static synchronized ChessGame getInstance(MoveStrategy pawnMoveStrategy, MoveStrategy knightMoveStrategy,
                                                   MoveStrategy bishopMoveStrategy, MoveStrategy kingMoveStrategy,
                                                   MoveStrategy queenMoveStrategy, MoveStrategy rookMoveStrategy) {
    if (instance == null) {
      return new ChessGame(pawnMoveStrategy, knightMoveStrategy, bishopMoveStrategy,
          kingMoveStrategy, queenMoveStrategy, rookMoveStrategy);
    }
    return instance;
  }

  private List<List<Box>> createBoard() {
    List<List<Box>> board = new ArrayList<>();
    for (int row = 0; row < 8; row++) {
      List<Box> currentRow = new ArrayList<>();
      board.add(currentRow);
    }

    // Row 0 - Black main pieces
    List<Box> row0 = List.of(
        new Box(new BlackRook(rookMoveStrategy), 0, 0),
        new Box(new BlackKnight(knightMoveStrategy), 0, 1),
        new Box(new BlackBishop(bishopMoveStrategy), 0, 2),
        new Box(new BlackQueen(queenMoveStrategy), 0, 3),
        new Box(new BlackKing(kingMoveStrategy), 0, 4),
        new Box(new BlackBishop(bishopMoveStrategy), 0, 5),
        new Box(new BlackKnight(knightMoveStrategy), 0, 6),
        new Box(new BlackRook(rookMoveStrategy), 0, 7)
    );

    // Row 1 - Black pawns
    List<Box> row1 = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      row1.add(new Box(new BlackPawn(pawnMoveStrategy), 1, i));
    }

    // Rows 2–5 - Empty
    for (int i = 2; i <= 5; i++) {
      List<Box> emptyRow = new ArrayList<>();
      for (int j = 0; j < 8; j++) {
        emptyRow.add(new Box(new EmptyPiece(), i, j));
      }
      board.set(i, emptyRow);
    }

    // Row 6 - White pawns
    List<Box> row6 = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      row6.add(new Box(new WhitePawn(pawnMoveStrategy), 6, i));
    }

    // Row 7 - White main pieces
    List<Box> row7 = List.of(
        new Box(new WhiteRook(rookMoveStrategy), 7, 0),
        new Box(new WhiteKnight(knightMoveStrategy), 7, 1),
        new Box(new WhiteBishop(bishopMoveStrategy), 7, 2),
        new Box(new WhiteQueen(queenMoveStrategy), 7, 3),
        new Box(new WhiteKing(kingMoveStrategy), 7, 4),
        new Box(new WhiteBishop(bishopMoveStrategy), 7, 5),
        new Box(new WhiteKnight(knightMoveStrategy), 7, 6),
        new Box(new WhiteRook(rookMoveStrategy), 7, 7)
    );

    board.set(0, row0);
    board.set(1, row1);
    board.set(6, row6);
    board.set(7, row7);

    return board;
  }

  public void printCurrentBoard() {
    for (List<Box> row : board) {
      for (Box box : row) {
        if (box.getPiece().getPieceType().equals(PieceType.EMPTY)) {
          System.out.print(".. ");
        } else {
          System.out.print(box.getPiece().getName() + " ");
        }

      }
      System.out.println();
    }

    System.out.println();
    System.out.println();
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  public boolean makeMove(Player player, PlayerMove startMove, PlayerMove endMove) {
    Box startBox = board.get(startMove.getRow()).get(startMove.getCol());
    Box endBox = board.get(endMove.getRow()).get(endMove.getCol());

    Piece currentPiece = startBox.getPiece();
    if (!currentPiece.getColorType().equals(player.getColorType())) {
      // invalid piece is picked
      log.error("Invalid piece is picked");
      return false;
    }

    if (!currentPiece.isValidMove(startBox, endBox)) {
      // invalid move for this piece
      log.error("Invalid move for this piece");
      return false;
    }

    if (!endBox.getPiece().getPieceType().equals(PieceType.EMPTY)) {
      // if the end box is not empty, check if the piece is of the same color
      if (endBox.getPiece().getColorType().equals(currentPiece.getColorType())) {
        log.error("Invalid move for this piece");
        return false;
      } else {
        // kill the opponent's piece
        if (endBox.getPiece().getPieceType().equals(PieceType.KING)) {
          gameStatus = GameStatus.CHECKMATE;
        }
        endBox.getPiece().setKilled(true);
      }
    }

    // move the piece
    endBox.setPiece(currentPiece);
    startBox.setPiece(new EmptyPiece()); // Clear the start box
    return true;
  }

  public void playGame() {
    while (true) {
      System.out.println("Current Board State");
      printCurrentBoard();

      Player currentPlayer = players.poll();
      System.out.println(currentPlayer.getName() + "'s turn. Enter row and column (0-indexed):");

      int row1 = scanner.nextInt();
      int col1 = scanner.nextInt();

      System.out.println("Enter row and column for the end position (0-indexed):");

      int row2 = scanner.nextInt();
      int col2 = scanner.nextInt();

      PlayerMove startMove = new PlayerMove(row1, col1);
      PlayerMove endMove = new PlayerMove(row2, col2);

      if (makeMove(currentPlayer, startMove, endMove)) {
        System.out.println(
            currentPlayer.getName() + " made a move from (" + row1 + ", " + col1 + ") to (" + row2 + ", " + col2 + ")");
      } else {
        System.out.println("Invalid move. Try again.");
        players.addFirst(currentPlayer); // player should make the move again
        continue;
      }

      players.add(currentPlayer);

      if (gameStatus.equals(GameStatus.CHECKMATE)) {
        System.out.println(currentPlayer.getName() + " wins!");
        break;
      }
    }
  }
}
