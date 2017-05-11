package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

/**
 * Created by markbrown on 5/10/17.
 */
public class RouletteCoreGameplayEngine {




    public static String rouletteGameEngineSetup() {
        System.out.println("Welcome to Roulette!");
        String numberOfPlayers = InputHandler.getStringInput("How many players? Max is 2.");
        switch (numberOfPlayers) {
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                return rouletteGameEngineSetup();
        }
    }




    public static ArrayList<RoulettePlayer> createRoulettePlayerList(int numberOfPlayers) {
        ArrayList<RoulettePlayer> roulettePlayers = new ArrayList<RoulettePlayer>();
        for (int i = 0; i < numberOfPlayers; i++) {
            String roulettePlayerID = InputHandler.getStringInput("Please enter your ID.");
            if (NumberUtils.isParsable(roulettePlayerID)) {
                Account roulettePlayerAccount = AccountManager.findAccount(Long.parseLong(roulettePlayerID));
                if (roulettePlayerAccount == null) {
                    System.out.println("ID not found!");
                    continue;
                }
                roulettePlayers.add(new RoulettePlayer(roulettePlayerAccount.getAccountBalance(), roulettePlayerAccount.getId(), returnEmptyRouletteBetList()));
            }
            else {
                continue;
            }
        }
        return roulettePlayers;
    }




    public static ArrayList<RouletteBet> returnEmptyRouletteBetList() {return new ArrayList<RouletteBet>();}





    private static void printTableInformation() {
        RouletteTable.printRouletteTable();
        RouletteTable.printInsideBets();
        RouletteTable.printOutsideBets();
    }



    public static boolean exitOpportunity() {
        String exitOpportunity = InputHandler.getStringInput("Type 'exit' before the round starts to leave game.");
        if (exitOpportunity.equalsIgnoreCase("exit")) {return false;}
        else {return true;}
    }



    public static void gatherPlayerBets(ArrayList<RoulettePlayer> roulettePlayers) {
        for (int i = 0; i < roulettePlayers.size(); i++) {



        }
    }



    private static void rouletteRoundEngineForOnePlayer() {
        boolean stillBetting = true;
        while (stillBetting) {
            rouletteRoundBettingEngineForOnePlayer();
            String keepBetting = keepBettingLoop();
            if (keepBetting.equalsIgnoreCase("Yes")) {
                rouletteRoundBettingEngineForOnePlayer();
            }
            else if (keepBetting.equalsIgnoreCase("No")) {
                stillBetting = false;
            }
            else {
                System.out.println("Not a valid answer.");
                keepBettingLoop();
            }
        }
    }



    public static String keepBettingLoop() {
        return InputHandler.getStringInput("Do you want to place another bet? Type 'yes' or 'no'.");
    }



    public static void rouletteRoundBettingEngineForOnePlayer() {
        String newBetType = RouletteBetHandler.handleAnyBet();
        String betValue = InputHandler.getStringInput("How much would you like to put down for this bet?");
        double betValueAsDouble = Double.parseDouble(roulettePlayers.get(0).placeBet(betValue));
        roulettePlayers.get(0).makeRouletteBet(newBetType, betValueAsDouble);
    }







}
