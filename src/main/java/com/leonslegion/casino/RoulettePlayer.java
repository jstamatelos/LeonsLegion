package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by markbrown on 5/9/17.
 */
public class RoulettePlayer extends Player {



    private ArrayList<RouletteBet> betList = new ArrayList<RouletteBet>();



    public RoulettePlayer (double balance, long accountId) {super(balance, accountId);}


    public ArrayList<RouletteBet> getBetList() {return betList;}



    public double placeBet (double bet) throws Exception {
        if(super.getBalance() - bet < 0) {
            throw new Exception("Bet is too large.");
        }
        super.setBalance(super.getBalance() - bet);
        return bet;
    }



    public void makeRouletteBet (String betType, double betValue) {
        betList.add(new RouletteBet(betType, betValue));
    }



}
