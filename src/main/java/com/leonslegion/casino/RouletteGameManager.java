package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.*;


public class RouletteGameManager {




    private static ArrayList<RoulettePlayer> roulettePlayers = new ArrayList<RoulettePlayer>();
    public static ArrayList<RouletteBet> returnEmptyRouletteBetList () {return new ArrayList<RouletteBet>();}
    public static void addRoulettePlayer() {
        InputHandler input = new InputHandler();
        String roulettePlayerID = input.getStringInput("Please enter your ID.");
        if (NumberUtils.isParsable(roulettePlayerID)) {
            Account roulettePlayerAccount = AccountManager.findAccount(Long.parseLong(roulettePlayerID));
            if (roulettePlayerAccount == null) {
                System.out.println("ID not found!");
                addRoulettePlayer();
            }

            roulettePlayers.add(new RoulettePlayer(roulettePlayerAccount.getAccountBalance(), roulettePlayerAccount.getId(), returnEmptyRouletteBetList()));
        }
        else {
            addRoulettePlayer();
        }
    }
    public static boolean exitOpportunity() {
        InputHandler inputHandler = new InputHandler();
        String exitOpportunity = inputHandler.getStringInput("Type 'exit' before the round starts to leave game.");
        if (exitOpportunity.equalsIgnoreCase("exit")) {return false;}
        else {return true;}
    }




    public static void rouletteGameEngineSetup() {
        System.out.println("Welcome to Roulette!");
        System.out.println("We can only accept one player for now.");
        InputHandler input = new InputHandler();
        String numberOfPlayers = input.getStringInput("How many players? Max is 2.");
        if (numberOfPlayers.equals("1")) {
            rouletteGameEngineForOnePlayer();
        }
        else if (numberOfPlayers.equals("2")) {
            //rouletteGameEngineForTwoPlayers();
        }
        else {
            System.out.println("That's not a valid number of players.");
            System.out.println();
            rouletteGameEngineSetup();
        }
    }




    public static void rouletteGameEngineForOnePlayer() {
        boolean engineOn = true;
        RouletteGame newGame = new RouletteGame();
        RouletteGame.initializeGame();
        while (engineOn) {
            engineOn = exitOpportunity();
            rouletteRoundEngineForOnePlayer();
            String spinResult = newGame.spin();
            checkPlayerBetsForResults(roulettePlayers.get(0), spinResult);
        }
    }
    public static void rouletteRoundEngineForOnePlayer() {
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
    public static void rouletteRoundBettingEngineForOnePlayer() {
        InputHandler inputHandler = new InputHandler();
        String newBetType = RouletteGame.handleAnyBet();
        String betValue = inputHandler.getStringInput("How much would you like to put down for this bet?");
        double betValueAsDouble = Double.parseDouble(roulettePlayers.get(0).placeBet(betValue));
        roulettePlayers.get(0).makeRouletteBet(newBetType, betValueAsDouble);
    }




    public static String keepBettingLoop() {
        InputHandler inputHandler = new InputHandler();
        return inputHandler.getStringInput("Do you want to place another bet? Type 'yes' or 'no'.");
    }



    public static void checkPlayerBetsForResults(RoulettePlayer playerOne, String spinResult) {
        System.out.println("The ball landed in: " + spinResult);
        System.out.println();
        RouletteBetChecker.checkPlayerBetsForInsideBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForOutsideDozenBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForOutsideColumnBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForEvenOrOddBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForFrontOrBackBetWins(playerOne, spinResult);

    }



}
