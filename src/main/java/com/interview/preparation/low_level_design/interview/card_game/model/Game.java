package com.interview.preparation.low_level_design.interview.card_game.model;

import com.interview.preparation.low_level_design.interview.card_game.exception.BadRequest;
import com.interview.preparation.low_level_design.interview.card_game.model.card.Card;
import com.interview.preparation.low_level_design.interview.card_game.model.card.CardColor;
import com.interview.preparation.low_level_design.interview.card_game.model.card.CardType;
import com.interview.preparation.low_level_design.interview.card_game.model.card.CardValue;

import java.util.*;

public class Game {
    private final Stack<Card> deck;

    public Game(){
        this.deck = new Stack<>();
        createDeck(deck);
        shuffleTheDeck();
    }

    private void createDeck(Stack<Card>deck){
        // RED hearts
        for(int i=1;i<=13;i++){
            Card card = new Card(CardValue.findByValue(i), CardColor.RED , CardType.HEART);
            deck.push(card);
        }
        // RED diamonds
        for(int i=1;i<=13;i++){
            Card card = new Card(CardValue.findByValue(i),CardColor.RED , CardType.DIAMOND);
            deck.push(card);
        }
        //BLACK spade
        for(int i=1;i<=13;i++){
            Card card = new Card(CardValue.findByValue(i),CardColor.BLACK , CardType.SPADE);
            deck.push(card);
        }
        //BLACK club
        for(int i=1;i<=13;i++){
            Card card = new Card(CardValue.findByValue(i),CardColor.BLACK , CardType.CLUB);
            deck.push(card);
        }
    }

    public Card popCardFromTop() throws BadRequest {
        if(deck.empty()){
            throw new BadRequest("Deck Is Empty At The Moment");
        }
        return deck.pop();
    }

    public void shuffleTheDeck(){
        List<Card> cards = new ArrayList<>();
        while(!deck.empty()){
            cards.add(deck.pop());
        }
        Collections.shuffle(cards);
        for(Card card : cards){
            deck.push(card);
        }
    }

    public void distributeCardsInAllPlayers(List<Player>players) throws BadRequest {
        if(players.size()==0 || deck.size()%players.size()!=0){
            throw new BadRequest("Please provide correct player list");
        }
        Random rand = new Random();
        int deckSize = deck.size();
        while(!deck.empty()){
            Card card = deck.peek();
            int playerIndex = rand.nextInt(4);
            if(players.get(playerIndex).getCards().size() < deckSize/players.size()){
                players.get(playerIndex).getCards().add(card);
                deck.pop();
            }
        }
    }
}
