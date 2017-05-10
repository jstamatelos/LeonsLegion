package com.leonslegion.casino;

/**
 * Created by markbrown on 5/9/17.
 */
public class RoulettePlayer extends Player {

    public RoulettePlayer (double balance, long accountId) {super(balance, accountId);}

    public double placeBet (double bet) throws Exception {
        if(super.getBalance() - bet < 0) {
            throw new Exception("Bet is too large.");
        }
        setBalance(getBalance() - bet);
        return bet;
    }
}
