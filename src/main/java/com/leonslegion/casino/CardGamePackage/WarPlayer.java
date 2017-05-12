package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.CardGamePackage.CardPlayer;

/**
 * Created by jarrydstamatelos on 5/10/17.
 */
public class WarPlayer extends CardPlayer {

    WarPlayer(double balance, long accountId) {
        super(balance, accountId);
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



