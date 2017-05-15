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



    public static void doesPlayerWantToExit (ArrayList<RoulettePlayer> roulettePlayers, InputAsker asker) {
        for (int count = 0; count < roulettePlayers.size(); count++) {
            Console.println("Does Player #" + roulettePlayers.get(count).getAccount().getId() + " want to exit?");
            if (RouletteCoreGameplayEngine.exitInput(asker)) {
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " has exited.");
                long remainingBalance = roulettePlayers.get(count).getBalance();
                long accountID = roulettePlayers.get(count).getAccount().getId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        long originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        long balanceDifference = remainingBalance - originalBalance;
                        Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
                    }
                }
                roulettePlayers.remove(roulettePlayers.get(count));
                count --;
            }
        }
    }
    public static void doesPlayerHaveABalance (ArrayList<RoulettePlayer> roulettePlayers) {
        for (int count = 0; count < roulettePlayers.size(); count++) {
            if (roulettePlayers.get(count).getBalance() <= 0) {
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " has no money!");
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " removed!");
                long remainingBalance = 0;
                long accountID = roulettePlayers.get(count).getAccount().getId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        long originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        long balanceDifference = -originalBalance;
                        Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
                    }
                }
                roulettePlayers.remove(roulettePlayers.get(count));
                count--;
            }
        }
    }
    // Fully Tested, including:
    // testThatPlayerCanExit, testThatPlayerCanStay
    public static boolean exitInput(InputAsker asker) {
        String exitOpportunity = asker.askForInput("Type 'exit' before the round starts to leave game. Or type any other letter to stay.");
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
            if (roulettePlayer.getBalance() == 0) {
                break;
            }
            String keepBetting = keepBettingLoop(new InputAsker(System.in, System.out));
            if (keepBetting.equalsIgnoreCase("Yes")) {
                rouletteRoundBettingEngineForOnePlayer(roulettePlayer);
            }
            else if (keepBetting.equalsIgnoreCase("No")) {
                stillBetting = false;
            }
            else {
                Console.println("Not a valid answer.");
            }
        }
    }
    private static void rouletteRoundBettingEngineForOnePlayer(RoulettePlayer roulettePlayer) {
        String newBetType = RouletteBetHandler.handleAnyBet(new InputAsker(System.in, System.out));
        String betValue = Console.getStringInput("How much would you like to put down for this bet? " +
                "Please use whole dollars.");
        String newBetValue = roulettePlayer.placeBet(betValue);
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        roulettePlayer.makeRouletteBet(newBetType, newBetValueAsLong);
        Console.printDashes();
        Console.print("Your balance is now: ");
        RoulettePrint.moneyFormatterForPrinting(roulettePlayer.getBalance());
        Console.println("");
        Console.println("You have placed the following bets:");
        for (int i = 0; i < roulettePlayer.getBetList().size(); i++) {
            RoulettePrint.moneyFormatterForPrinting(roulettePlayer.getBetList().get(i).getBetValue());
            Console.print(" on " + roulettePlayer.getBetList().get(i).getBetType());
            Console.println("");
        }
        Console.println("");
    }
    public static String keepBettingLoop(InputAsker asker) {
        Console.println("If your balance is 0, the roulette wheel will spin.");
        return asker.askForInput("Do you want to place another bet? Type 'yes' or 'no'.");
    }




    public String spin() {
        int randomNumber = (int) Math.floor(Math.random()*37);
        return Integer.toString(randomNumber);
    }



    public static void checkPlayerBetsForResults(ArrayList<RoulettePlayer> roulettePlayers, String spinResult) {
        Console.println("");
        Console.println("The ball landed in: " + spinResult);
        for (int count = 0; count < roulettePlayers.size(); count++) {
            RoulettePlayer player = roulettePlayers.get(count);
            Console.printDashes();
            Console.println("Checking bets for Player #" + player.getAccount().getId());
            ArrayList<RouletteBet> betList = player.getBetList();
            for (int bet = 0; bet < betList.size(); bet++) {
                RoulettePrint.moneyFormatterForPrinting(betList.get(bet).getBetValue());
                Console.print(" on " + betList.get(bet).getBetType());
                Console.println("");
            }
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForInsideBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(betList, spinResult));
            Console.println("");
            Console.print("Player #" + roulettePlayers.get(count).getAccount().getId() + " new balance: ");
            RoulettePrint.moneyFormatterForPrinting(roulettePlayers.get(count).getBalance());
            Console.println("");
            Console.printDashes();
            player.resetBetList();
        }
    }

}
