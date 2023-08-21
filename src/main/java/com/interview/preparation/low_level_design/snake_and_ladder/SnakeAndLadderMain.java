package com.interview.preparation.low_level_design.snake_and_ladder;

import com.interview.preparation.low_level_design.snake_and_ladder.model.Game;
import com.interview.preparation.low_level_design.snake_and_ladder.model.Player;

public class SnakeAndLadderMain {
    public static void main(String[] args) {
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        Player p4 = new Player("P4");

        int noOfSnakes = 4;
        int noOfLadders = 4;
        int boardSize = 100;

        Game game = new Game(noOfSnakes ,noOfLadders , boardSize);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
        game.addPlayer(p4);
        game.playGame();
    }
}
