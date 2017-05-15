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


    public String placeBet (String bet) {
        while (!NumberUtils.isParsable(bet)) {
           bet = Console.getStringInput("That's not a valid bet.");
        }
        while (NumberUtils.isParsable(bet)) {
            if (Double.parseDouble(bet) < 1 && Double.parseDouble(bet) > 0) {
                bet = Console.getStringInput("Minimum bet is one dollar!");
            }
            else if (Double.parseDouble(bet) < 0) {
                bet = Console.getStringInput("You cannot make a negative bet!");
            }
            else if (getBalance() - Double.parseDouble(bet) < 0) {
                bet = Console.getStringInput("Your bet is greater than your balance!");
            }
            else {
                double betAsDouble = Double.parseDouble(bet);
                long betAsLong = (long) betAsDouble;
                getAccount().setAccountBalance(betAsLong * 100 * -1);
                return bet;
            }
        }
        return bet;
    }




    public void makeRouletteBet (String betType, long betValue) {
        betList.add(new RouletteBet(betType, betValue));
    }


}
