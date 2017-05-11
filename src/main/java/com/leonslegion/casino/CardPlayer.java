package com.leonslegion.casino;

public abstract class CardPlayer extends Player {

    protected Hand hand;

    CardPlayer(double balance, long accountId) {
        super(balance, accountId);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public double placeBet(double bet) throws Exception {
        if(getBalance() - bet < 0) {
            throw new Exception("Bet is too large.");
        }
        setBalance(getBalance() - bet);
        return bet;
    }

}