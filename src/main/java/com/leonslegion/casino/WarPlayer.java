package com.leonslegion.casino;

/**
 * Created by jarrydstamatelos on 5/10/17.
 */
public class WarPlayer extends CardPlayer {

    WarPlayer(double balance, long accountId) {
        super(balance, accountId);
    }

    public String playerShowCard(Card card){
        return card.toString();
    }

    public String dealerShowCard(Card card){
        return card.toString();
    }

}
