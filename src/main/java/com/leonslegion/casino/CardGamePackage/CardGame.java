package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.Abstracts.CasinoGame;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public abstract class CardGame extends CasinoGame {

    Deck deck = new Deck();

    public Deck getDeck() {
        return deck;
    }

    public double placeBet(double bet){
        return bet;
    }

    public abstract void initialDeal();


}
