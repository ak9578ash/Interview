package com.interview.preparation.low_level_design.interview.card_game.model;

import com.interview.preparation.low_level_design.interview.card_game.model.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Player {
    private final String id;
    private final String name;
    @Setter
    private List<Card> cards;

    public Player(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cards = new ArrayList<>();
    }
}
