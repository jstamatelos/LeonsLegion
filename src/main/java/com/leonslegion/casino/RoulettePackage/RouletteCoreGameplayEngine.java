package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.Abstracts.Spin;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

/**
 * Created by markbrown on 5/10/17.
 */
public class RouletteCoreGameplayEngine implements Spin {




    public static String rouletteGameEngineSetup() {
        Console.println("Welcome to Roulette!");
        String numberOfPlayers = InputHandler.getStringInput("How many players? Max is 2.");
        switch (numberOfPlayers) {
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                Console.println("Not a valid selection");
                Console.printDashes();
                return rouletteGameEngineSetup();
        }
    }




    public static ArrayList<RoulettePlayer> createRoulettePlayerList(int numberOfPlayers) {
        ArrayList<RoulettePlayer> roulettePlayers = new ArrayList<RoulettePlayer>();
        int count = 0;
        while (count < numberOfPlayers) {
            String roulettePlayerID = InputHandler.getStringInput("Please enter your ID.");
            if (!NumberUtils.isParsable(roulettePlayerID)) {
                Console.println("Not a valid ID");
                continue;
            }
            Account roulettePlayerAccount = Account.AccountManager.findAccount(Long.parseLong(roulettePlayerID));
            if (roulettePlayerAccount == null) {
                Console.println("ID not found!");
                continue;
            }
            if ((roulettePlayers.size() == 1) && roulettePlayerID.equalsIgnoreCase(Long.toString(roulettePlayers.get(0).getAccount().getId()))) {
                Console.println("ID already in list");
                continue;
            }

            Console.printDashes();
            Console.println("ID accepted!");
            Console.printDashes();
            roulettePlayers.add(new RoulettePlayer(roulettePlayerAccount, returnEmptyRouletteBetList()));
            count++;
        }
        return roulettePlayers;
    }




    public static ArrayList<RouletteBet> returnEmptyRouletteBetList() {return new ArrayList<RouletteBet>();}





    private static void printTableInformation() {
        RouletteTable.printRouletteTable();
        RouletteTable.printInsideBets();
        RouletteTable.printOutsideBets();
    }



    public static boolean exitInput() {
        String exitOpportunity = InputHandler.getStringInput("Type 'exit' before the round starts to leave game. Or type any other letter to stay.");
        if (exitOpportunity.equalsIgnoreCase("exit")) {return true;}
        else {return false;}
    }



    public static void gatherPlayerBets(ArrayList<RoulettePlayer> roulettePlayers) {
        for (int i = 0; i < roulettePlayers.size(); i++) {
            printTableInformation();
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
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        roulettePlayer.makeRouletteBet(newBetType, newBetValueAsDouble);
        Console.printDashes();
        System.out.print("Your balance is now: $");
        System.out.printf("%,.2f", roulettePlayer.getBalance());
        Console.printDashes();
        Console.println("You have placed the following bets:");
        for (int i = 0; i < roulettePlayer.getBetList().size(); i++) {
            System.out.print("$");
            System.out.printf("%,.2f", roulettePlayer.getBetList().get(i).getBetValue());
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
