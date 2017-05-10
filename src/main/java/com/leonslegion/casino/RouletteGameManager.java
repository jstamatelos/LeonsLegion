package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.*;

/**
 * Created by danielprahl on 5/9/17.
 */
public class RouletteGameManager extends GameManager {



    public RouletteGameManager(AccountManager accountManager){
        super(accountManager);
    }



    public static ArrayList<RouletteBet> returnEmptyRouletteBetList () {return new ArrayList<RouletteBet>();}



    public static RoulettePlayer addRoulettePlayer() {
        InputHandler input = new InputHandler();
        String roulettePlayerID = input.getStringInput("Please enter your ID.");
        if (NumberUtils.isParsable(roulettePlayerID)) {
            Account roulettePlayerAccount = AccountManager.findAccount(Long.parseLong(roulettePlayerID));
            if (roulettePlayerAccount == null) {
                System.out.println("ID not found!");
                return addRoulettePlayer();
            }
            return new RoulettePlayer(roulettePlayerAccount.getAccountBalance(), roulettePlayerAccount.getId(), returnEmptyRouletteBetList());
        }
        else {
            return addRoulettePlayer();
        }
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
        RoulettePlayer playerOne = addRoulettePlayer();
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
        checkPlayerBetsForOutsideDozenBetWins(playerOne, spinResult);
        checkPlayerBetsForOutsideColumnBetWins(playerOne, spinResult);
        checkPlayerBetsForEvenOrOddBetWins(playerOne, spinResult);
        checkPlayerBetsForFrontOrBackBetWins(playerOne, spinResult);

    }



    public static void checkPlayerBetsForInsideBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (bet.getBetType().equals(spinResult)) {
                System.out.println("You won a 35:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*35);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*35));
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.println("Your new balance: $");
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
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*3));
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) > 12 && Integer.parseInt(spinResult) < 25 && bet.getBetType().equals("2nd D")) {
                System.out.println("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*3));
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) > 24 && Integer.parseInt(spinResult) < 37 && bet.getBetType().equals("3rd D")) {
                System.out.println("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*3));
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }



    public static void checkPlayerBetsForOutsideColumnBetWins(RoulettePlayer playerOne, String spinResult) {
        double tempBalance = playerOne.getBalance();
        for (RouletteBet bet : playerOne.getBetList()) {
            for (int columnStart = 1; columnStart < 35; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && bet.getBetType().equals("1st C")) {
                    System.out.println("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", bet.getBetValue() * 2);
                    System.out.println();
                    playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue() * 3));
                    System.out.println("Your new balance: $");
                    System.out.printf("%,.2f", playerOne.getBalance());
                    System.out.println();
                }
            }
            for (int columnStart = 2; columnStart < 36; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && bet.getBetType().equals("2nd C")) {
                    System.out.println("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", bet.getBetValue() * 2);
                    System.out.println();
                    playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue() * 3));
                    System.out.println("Your new balance: ");
                    System.out.printf("%,.2f", playerOne.getBalance());
                    System.out.println();
                }
            }
            for (int columnStart = 3; columnStart < 37; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && bet.getBetType().equals("3rd C")) {
                    System.out.println("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", bet.getBetValue() * 2);
                    System.out.println();
                    playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue() * 3));
                    System.out.println("Your new balance: ");
                    System.out.printf("%,.2f", playerOne.getBalance());
                    System.out.println();
                }
            }
            if (tempBalance == playerOne.getBalance()) {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }

        }
    }



    public static void checkPlayerBetsForEvenOrOddBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (Integer.parseInt(spinResult) % 2 == 0 && Integer.parseInt(spinResult) != 0 && bet.getBetType().equals("Even")) {
                System.out.println("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) % 2 == 1 && Integer.parseInt(spinResult) != 0 && bet.getBetType().equals("Odd")) {
                System.out.println("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.println("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }



    public static void checkPlayerBetsForFrontOrBackBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 19 && bet.getBetType().equals("Front")) {
                System.out.println("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) > 18 && Integer.parseInt(spinResult) < 37 && bet.getBetType().equals("Back")) {
                System.out.println("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.println("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.println("Your new balance: $");
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
