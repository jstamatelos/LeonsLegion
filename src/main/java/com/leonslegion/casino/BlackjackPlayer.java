package com.leonslegion.casino;

public class BlackjackPlayer extends CardPlayer {

    BlackjackPlayer(double balance, long accountId) {
        super(balance, accountId);
    }

    void hit(Card card) {
        hand.addCard(card);
    }

    void stay() {
        //nothing happens. not sure if this needs a method.
    }
}