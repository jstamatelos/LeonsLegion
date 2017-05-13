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



    public static RoulettePlayer addRoulettePlayer(ArrayList<RoulettePlayer> roulettePlayers) {
        long numberOfAttempts = 2;
        while (numberOfAttempts > 0) {
            Console.printNumberOfAttemptsRemaining(numberOfAttempts);
            long ID = RouletteInputOutputAndPrint.getPlayerID();
            if (ID == -1 && numberOfAttempts == 2) {
                numberOfAttempts--;
                Console.printNumberOfAttemptsRemaining(numberOfAttempts);
                ID = RouletteInputOutputAndPrint.getPlayerID();
                continue;
            }
            if (ID == -1 && numberOfAttempts == 1) {
                Console.printAttemptsExceeded();
                numberOfAttempts = 2;
                continue;
            }
            Account roulettePlayerAccount = Account.AccountManager.findAccount(ID);
            if (roulettePlayerAccount == null && numberOfAttempts == 2) {
                numberOfAttempts--;
                Console.printAccountNotFoundMessage();
                continue;
            }
            if (roulettePlayerAccount == null && numberOfAttempts == 1) {
                Console.printAttemptsExceeded();
                numberOfAttempts = 2;
                continue;

            }
            if (roulettePlayers.size() == 1 && roulettePlayerAccount.getId() == roulettePlayers.get(0).getAccount().getId()) {
                if (numberOfAttempts == 2) {
                    Console.printAccountAlreadyLoaded();
                    numberOfAttempts--;
                }
                else {
                    Console.printAccountAlreadyLoaded();
                    Console.printAttemptsExceeded();
                    numberOfAttempts = 2;
                }
            }
            else {
                Console.printAccountAccepted();
                return new RoulettePlayer(roulettePlayerAccount, new ArrayList<RouletteBet>());}
        }
        return null;
    }



    public static ArrayList<RoulettePlayer> createRoulettePlayerList(long numberOfPlayers) {
        ArrayList<RoulettePlayer> players = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            RoulettePlayer newPlayer = addRoulettePlayer(players);
            players.add(newPlayer);
        }
        return players;
    }























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
