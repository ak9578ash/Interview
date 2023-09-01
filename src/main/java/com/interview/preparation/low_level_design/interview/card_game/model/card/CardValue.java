package com.interview.preparation.low_level_design.interview.card_game.model.card;

public enum CardValue {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private final int numVal;

    CardValue(int numVal) {
        this.numVal = numVal;
    }

    public static CardValue findByValue(int value) {
        CardValue result = null;
        for (CardValue cardValue : values()) {
            if (cardValue.numVal == value) {
                result = cardValue;
                break;
            }
        }
        return result;
    }
}
