package com.leonslegion.casino;

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



    public static void RouletteGameEngineSetup() {
        System.out.println("Welcome to Roulette!" + "\n");
        InputHandler input = new InputHandler();
        String numberOfPlayers = input.getStringInput("How many players? Max is 2.");
        if (numberOfPlayers.equals(1)) {
            RouletteGameEngineForOnePlayer();
        }
        else if (numberOfPlayers.equals(2)) {
            RoulettePlayer playerOne = RoulettePlayer.addRoulettePlayer();
            RoulettePlayer playerTwo = RoulettePlayer.addRoulettePlayer();
            RouletteGameEngineForTwoPlayers();
        }
        else {
            System.out.println("That's not a valid number of players.");
            System.out.println();
            RouletteGameEngineSetup();
        }
    }



    public static void RouletteRoundBettingEngineForOnePlayer() {
        InputHandler inputHandler = new InputHandler();
        RoulettePlayer playerOne = RoulettePlayer.addRoulettePlayer();
        String newBetType = RouletteGame.handleAnyBet();
        String betValue = inputHandler.getStringInput("How much would you like to put down for this bet?");
        double betValueAsDouble = Double.parseDouble(playerOne.placeBet(betValue));
        playerOne.makeRouletteBet(newBetType, betValueAsDouble);
    }



    public static void RouletteRoundEngineForOnePlayer() {
       boolean stillBetting = true;
       while (stillBetting) {
           RouletteRoundBettingEngineForOnePlayer();
       }
    }



    public static void RouletteGameEngineForOnePlayer() {
        InputHandler inputHandler = new InputHandler();
        boolean engineOn = true;
        RouletteGame.initializeGame();
        while (engineOn) {
            engineOn = RouletteGame.exitOpportunity();
            RouletteRoundEngineForOnePlayer();
        }
    }



    public static void RouletteGameEngineForTwoPlayers() {
        RouletteGame.initializeGame();
    }





}
