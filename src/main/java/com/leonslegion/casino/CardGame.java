package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public abstract class CardGame extends CasinoGame {


    ArrayList<Card> deck;

    public double placeBet(double bet){
        return bet;
    }

    public abstract void initialDeal();


}
