package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public abstract class CardGame extends CasinoGame {

    ArrayList<Card> deck;
    ArrayList<CardPlayer> players;

    public CardGame() {
        deck = Deck.createNewDeck();
        players = new ArrayList<>();
    }

    public abstract void initialDeal();



}
