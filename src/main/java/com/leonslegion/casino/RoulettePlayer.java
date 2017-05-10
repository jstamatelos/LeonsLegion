package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by markbrown on 5/9/17.
 */


public class RoulettePlayer extends Player {



    private ArrayList<RouletteBet> betList = new ArrayList<RouletteBet>();



    private RoulettePlayer (double balance, long accountId, ArrayList<RouletteBet> betList) {
        super(balance, accountId);
        this.betList = betList;
    }



/*
    public static RoulettePlayer addRoulettePlayer() {
        InputHandler input = new InputHandler();
        String roulettePlayerID = input.getStringInput("Please enter your ID.");

        //Method to search account manager for name
        //Method that returns player object with accountID that matches above input

    }
*/

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
