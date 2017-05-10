package com.leonslegion.casino;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by danielprahl on 5/9/17.
 */
public class RouletteGameManager extends GameManager {



    public RouletteGameManager() {
        // no-arg constructor
    }




    public RouletteGameManager(AccountManager accountManager){

        super(accountManager);
    }




    public static void rouletteGameEngineSetup() {
        System.out.println("Welcome to Roulette!" + "\n");
        InputHandler input = new InputHandler();
        String numberOfPlayers = input.getStringInput("How many players? Max is 2.");
        if (numberOfPlayers.equals(1)) {
            rouletteGameEngineForOnePlayer();
        }
        else if (numberOfPlayers.equals(2)) {
            RoulettePlayer playerOne = RoulettePlayer.addRoulettePlayer();
            RoulettePlayer playerTwo = RoulettePlayer.addRoulettePlayer();
            rouletteGameEngineForTwoPlayers();
        }
        else {
            System.out.println("That's not a valid number of players.");
            System.out.println();
            rouletteGameEngineSetup();
        }
    }


    public static String keepBettingLoop() {
        InputHandler inputHandler = new InputHandler();
        return inputHandler.getStringInput("Do you want to place another bet? Type 'yes' or 'no'.");
    }


    public static void rouletteRoundBettingEngineForOnePlayer() {
        InputHandler inputHandler = new InputHandler();
        RoulettePlayer playerOne = RoulettePlayer.addRoulettePlayer();
        String newBetType = RouletteGame.handleAnyBet();
        String betValue = inputHandler.getStringInput("How much would you like to put down for this bet?");
        double betValueAsDouble = Double.parseDouble(playerOne.placeBet(betValue));
        playerOne.makeRouletteBet(newBetType, betValueAsDouble);
    }



    public static void rouletteRoundEngineForOnePlayer() {
       boolean stillBetting = true;
       while (stillBetting) {
           rouletteRoundBettingEngineForOnePlayer();
           String keepBetting = keepBettingLoop();
           if (keepBetting.equalsIgnoreCase("Yes")) {

           }
       }
    }



    public static void rouletteGameEngineForOnePlayer() {
        InputHandler inputHandler = new InputHandler();
        boolean engineOn = true;
        RouletteGame.initializeGame();
        while (engineOn) {
            engineOn = RouletteGame.exitOpportunity();
            rouletteRoundEngineForOnePlayer();
        }
    }



    public static void rouletteGameEngineForTwoPlayers() {
        RouletteGame.initializeGame();
    }


}
