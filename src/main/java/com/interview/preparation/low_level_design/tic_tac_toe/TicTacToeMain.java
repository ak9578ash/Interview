package com.interview.preparation.low_level_design.tic_tac_toe;

import com.interview.preparation.low_level_design.tic_tac_toe.model.Game;
import com.interview.preparation.low_level_design.tic_tac_toe.model.PieceType;
import com.interview.preparation.low_level_design.tic_tac_toe.model.Player;

public class TicTacToeMain {
  public static void main(String[] args) {
    Player player1 = new Player("Akash", PieceType.CROSS);
    Player player2 = new Player("Anuj", PieceType.CIRCLE);

    Game game = Game.getInstance();

    game.addPlayers(player1);
    game.addPlayers(player2);
    game.playGame();
  }
}
