package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by danielprahl on 5/9/17.
 */
public class RouletteGameManager extends GameManager {



    public RouletteGameManager(AccountManager accountManager){
        super(accountManager);
    }




    public static void rouletteGameEngineSetup() {
        System.out.println("Welcome to Roulette!" + "\n");
        InputHandler input = new InputHandler();
        String numberOfPlayers = input.getStringInput("How many players? Max is 2.");
        if (numberOfPlayers.equals("1")) {
            rouletteGameEngineForOnePlayer();
        }
        else if (numberOfPlayers.equals("2")) {
            rouletteGameEngineForTwoPlayers();
        }
        else {
            System.out.println("That's not a valid number of players.");
            System.out.println();
            rouletteGameEngineSetup();
        }
    }



    public static void rouletteGameEngineForOnePlayer() {
        InputHandler inputHandler = new InputHandler();
        boolean engineOn = true;
        RouletteGame game = new RouletteGame();
        RouletteGame.initializeGame();
        while (engineOn) {
            engineOn = exitOpportunity();
            rouletteRoundEngineForOnePlayer();
            String spinOutcome = game.spin();

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
        RoulettePlayer playerOne = RoulettePlayer.addRoulettePlayer();
        String newBetType = RouletteGame.handleAnyBet();
        String betValue = inputHandler.getStringInput("How much would you like to put down for this bet?");
        double betValueAsDouble = Double.parseDouble(playerOne.placeBet(betValue));
        playerOne.makeRouletteBet(newBetType, betValueAsDouble);
    }



    public static String keepBettingLoop() {
        InputHandler inputHandler = new InputHandler();
        return inputHandler.getStringInput("Do you want to place another bet? Type 'yes' or 'no'.");
    }



    public static void checkPlayerBetsForResults(RoulettePlayer playerOne, String spinResult) {
        System.out.println("The ball landed in: " + spinResult);
        System.out.println();
        checkPlayerBetsForInsideBetWins(playerOne, spinResult);
    }



    public static void checkPlayerBetsForInsideBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (bet.getBetType().equals(spinResult)) {
                System.out.println("You won a 35:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*35);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()*35));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }



    public static void checkPlayerBetsForOutsideDozenBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 13 && bet.getBetType().equals("1st D")) {
                System.out.println("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()*2));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }

            if (Integer.parseInt(spinResult) > 12 && Integer.parseInt(spinResult) < 25 && bet.getBetType().equals("2nd D")) {
                System.out.println("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()*2));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }

            if (Integer.parseInt(spinResult) > 24 && Integer.parseInt(spinResult) < 37 && bet.getBetType().equals("3rd D")) {
                System.out.println("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()*2));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }



    public static void rouletteGameEngineForTwoPlayers() {
        RouletteGame.initializeGame();
    }



    public static boolean exitOpportunity() {
        InputHandler inputHandler = new InputHandler();
        String exitOpportunity = inputHandler.getStringInput("Type 'exit' before the round starts to leave game.");
        if (exitOpportunity.equalsIgnoreCase("exit")) {return false;}
        else {return true;}
    }

}
