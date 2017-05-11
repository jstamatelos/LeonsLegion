package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

/**
 * Created by markbrown on 5/10/17.
 */
public class RouletteCoreGameplayEngine implements Spin {




    public static String rouletteGameEngineSetup() {
        System.out.println("Welcome to Roulette!");
        String numberOfPlayers = InputHandler.getStringInput("How many players? Max is 2.");
        switch (numberOfPlayers) {
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                System.out.println("Not a valid selection");
                System.out.println();
                return rouletteGameEngineSetup();
        }
    }




    public static ArrayList<RoulettePlayer> createRoulettePlayerList(int numberOfPlayers) {
        ArrayList<RoulettePlayer> roulettePlayers = new ArrayList<RoulettePlayer>();
        int count = 0;
        while (count < numberOfPlayers) {
            String roulettePlayerID = InputHandler.getStringInput("Please enter your ID.");
            if (!NumberUtils.isParsable(roulettePlayerID)) {
                System.out.println("Not a valid ID");
                continue;
            }
            Account roulettePlayerAccount = AccountManager.findAccount(Long.parseLong(roulettePlayerID));
            if (roulettePlayerAccount == null) {
                System.out.println("ID not found!");
                continue;
            }
            System.out.println();
            System.out.println("ID accepted!");
            System.out.println();
            roulettePlayers.add(new RoulettePlayer(roulettePlayerAccount.getAccountBalance(), roulettePlayerAccount.getId(), returnEmptyRouletteBetList()));
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
            System.out.println("Now Betting For Player #" + roulettePlayers.get(i).getAccountId());
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
                System.out.println("Not a valid answer.");
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
        System.out.println();
        System.out.print("Your balance is now: $");
        System.out.printf("%,.2f", roulettePlayer.getBalance());
        System.out.println();
        System.out.print("You have placed the following bets:");
        for (int i = 0; i < roulettePlayer.getBetList().size(); i++) {
            System.out.println();
            System.out.printf("%,.2f", roulettePlayer.getBetList().get(i).getBetValue());
            System.out.print(" on " + roulettePlayer.getBetList().get(i).getBetType());
            System.out.println();

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
