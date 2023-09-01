package com.interview.preparation.low_level_design.interview.card_game.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private CardValue cardValue;
    private CardColor cardColor;
    private CardType cardType;

    public Card(CardValue cardValue, CardColor cardColor, CardType cardType) {
        this.cardValue = cardValue;
        this.cardColor = cardColor;
        this.cardType = cardType;
    }
}
