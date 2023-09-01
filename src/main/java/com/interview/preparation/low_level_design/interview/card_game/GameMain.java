package com.interview.preparation.low_level_design.interview.card_game;

import com.interview.preparation.low_level_design.interview.card_game.exception.BadRequest;
import com.interview.preparation.low_level_design.interview.card_game.model.Game;
import com.interview.preparation.low_level_design.interview.card_game.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMain {
    public static void main(String[] args) throws BadRequest {
        Game game = new Game();

        Player p1 = new Player("test1");
        Player p2 = new Player("test2");
        Player p3 = new Player("test3");
        Player p4 = new Player("test4");

//        Card topCard = game.popCardFromTop();
//        System.out.println(topCard.getCardColor());

        List<Player> playerList = new ArrayList<>();
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);

        game.distributeCardsInAllPlayers(playerList);
    }
}
