package com.leonslegion.casino;

public class BlackjackPlayer extends CardPlayer {

    BlackjackPlayer(double balance, long accountId) {
        super(balance, accountId);
    }

    void hit() {
        //add a card from the deck to hand
    }

    void stay() {
        //nothing happens. not sure if this needs a method.
    }
}