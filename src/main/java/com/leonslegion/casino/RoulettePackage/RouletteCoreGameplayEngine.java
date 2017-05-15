package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.Abstracts.Spin;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;
import java.util.ArrayList;

/**
 * Created by markbrown on 5/10/17.
 */
public class RouletteCoreGameplayEngine implements Spin {

    public static boolean exitInput() {
        String exitOpportunity = InputHandler.getStringInput("Type 'exit' before the round starts to leave game. Or type any other letter to stay.");
        if (exitOpportunity.equalsIgnoreCase("exit")) {return true;}
        else {return false;}
    }



    public static void gatherPlayerBets(ArrayList<RoulettePlayer> roulettePlayers) {
        for (int i = 0; i < roulettePlayers.size(); i++) {
            RoulettePrint.printTableInformation();
            Console.println("Now Betting For Player #" + roulettePlayers.get(i).getAccount().getId());
            gatherEachPlayersBets(roulettePlayers.get(i));
        }
    }



    private static void gatherEachPlayersBets(RoulettePlayer roulettePlayer) {
        boolean stillBetting = true;
        while (stillBetting) {
            String keepBetting = keepBettingLoop();
            if (keepBetting.equalsIgnoreCase("Yes")) {
                rouletteRoundBettingEngineForOnePlayer(roulettePlayer);
            }
            else if (keepBetting.equalsIgnoreCase("No")) {
                stillBetting = false;
            }
            else {
                Console.println("Not a valid answer.");
                continue;
            }
        }
    }




    private static void rouletteRoundBettingEngineForOnePlayer(RoulettePlayer roulettePlayer) {
        String newBetType = RouletteBetHandler.handleAnyBet();
        String betValue = InputHandler.getStringInput("How much would you like to put down for this bet?");
        String newBetValue = roulettePlayer.placeBet(betValue);
        long newBetValueAsDouble = Long.parseLong(newBetValue);
        roulettePlayer.makeRouletteBet(newBetType, newBetValueAsDouble);
        Console.printDashes();
        Console.print("Your balance is now: ");
        Console.printMoney(roulettePlayer.getBalance());
        Console.printDashes();
        Console.println("You have placed the following bets:");
        for (int i = 0; i < roulettePlayer.getBetList().size(); i++) {
            Console.printMoney(roulettePlayer.getBetList().get(i).getBetValue());
            Console.println(" on " + roulettePlayer.getBetList().get(i).getBetType());
        }
    }


    public static String keepBettingLoop() {
        return InputHandler.getStringInput("Do you want to place another bet? Type 'yes' or 'no'.");
    }




    public String spin() {
        ArrayList<String> rouletteWheel = RouletteTable.createRouletteWheel();
        int randomNumber = (int) Math.floor(Math.random()*37);
        return rouletteWheel.get(randomNumber);
    }




}
