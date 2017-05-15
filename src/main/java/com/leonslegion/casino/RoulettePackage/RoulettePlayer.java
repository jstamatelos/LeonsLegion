package com.leonslegion.casino.RoulettePackage;

import java.util.ArrayList;

import com.leonslegion.casino.Abstracts.Player;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by markbrown on 5/9/17.
 */


public class RoulettePlayer extends Player {



    private ArrayList<RouletteBet> betList = new ArrayList<RouletteBet>();



    public RoulettePlayer (Account account, ArrayList<RouletteBet> betList) {
        super(account);
        this.betList = betList;
    }



    public ArrayList<RouletteBet> getBetList() {return betList;}
    public void resetBetList() {
        betList = new ArrayList<RouletteBet>();
    }


    public long placeBet (long bet) {
        if (super.getBalance() - bet < 0) {
            Console.println("Bet greater than current Balance!");
        }
        getAccount().setAccountBalance(getBalance() - bet);
        return bet;
    }


    //TODO - this logic should probably be left to the Console class.
    public String placeBet (String bet) {
        if (!NumberUtils.isParsable(bet)) {
            String newBet = InputHandler.getStringInput("That's not a valid bet.");
            return placeBet(newBet);
        }
        else if (Double.parseDouble(bet) < 0.01 && Double.parseDouble(bet) > 0) {
            String newBet = InputHandler.getStringInput("Minimum bet is one cent!");
            return placeBet(newBet);
        }
        else if (Double.parseDouble(bet) < 0) {
            String newBet = InputHandler.getStringInput("You cannot make a negative bet!");
            return placeBet(newBet);
        }
        else if (getBalance() - Double.parseDouble(bet) < 0) {
            String newBet = InputHandler.getStringInput("Your bet is greater than your balance!");
            return placeBet(newBet);
        }
        else {
            getAccount().setAccountBalance(Long.parseLong(bet) * -1);
        }
        return bet;
    }




    public void makeRouletteBet (String betType, long betValue) {
        betList.add(new RouletteBet(betType, betValue));
    }


}
