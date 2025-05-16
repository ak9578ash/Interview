package com.interview.preparation.low_level_design.chess_game;

import com.interview.preparation.low_level_design.chess_game.model.ChessGame;
import com.interview.preparation.low_level_design.chess_game.model.ColorType;
import com.interview.preparation.low_level_design.chess_game.model.Player;
import com.interview.preparation.low_level_design.chess_game.model.strategy.DefaultPawnMoveStrategy;
import com.interview.preparation.low_level_design.chess_game.model.strategy.MoveStrategy;

public class ChessGameMain {
  public static void main(String[] args) {
    MoveStrategy pawnMoveStrategy = new DefaultPawnMoveStrategy();
    MoveStrategy knightMoveStrategy = new DefaultPawnMoveStrategy();
    MoveStrategy bishopMoveStrategy = new DefaultPawnMoveStrategy();
    MoveStrategy kingMoveStrategy = new DefaultPawnMoveStrategy();
    MoveStrategy queenMoveStrategy = new DefaultPawnMoveStrategy();
    MoveStrategy rookMoveStrategy = new DefaultPawnMoveStrategy();

    ChessGame chessGame =
        new ChessGame(pawnMoveStrategy, knightMoveStrategy, bishopMoveStrategy, kingMoveStrategy, queenMoveStrategy,
            rookMoveStrategy);

    Player blackPlayer = new Player("Alok", ColorType.BLACK);
    Player whitePlayer = new Player("Apoorv", ColorType.WHITE);

    chessGame.addPlayer(whitePlayer);
    chessGame.addPlayer(blackPlayer);

    chessGame.playGame();
  }
}
