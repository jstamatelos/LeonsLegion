package com.leonslegion.casino;

<<<<<<< HEAD
/**
 * Created by markbrown on 5/9/17.
 */
public class RoulettePlayer extends Player {

    public RoulettePlayer (double balance, long accountId) {super(balance, accountId);}
=======
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


>>>>>>> e6007cb4a6d856cdcbd1b295ddbd87e15fa85664

    public double placeBet (double bet) throws Exception {
        if(super.getBalance() - bet < 0) {
            throw new Exception("Bet is too large.");
        }
<<<<<<< HEAD
        setBalance(getBalance() - bet);
        return bet;
    }
=======
        super.setBalance(super.getBalance() - bet);
        return bet;
    }



    public void makeRouletteBet (String betType, double betValue) {
        betList.add(new RouletteBet(betType, betValue));
    }



>>>>>>> e6007cb4a6d856cdcbd1b295ddbd87e15fa85664
}
