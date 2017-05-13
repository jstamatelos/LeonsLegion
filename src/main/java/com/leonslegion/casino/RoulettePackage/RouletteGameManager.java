package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

import java.util.*;


public class RouletteGameManager {



    public static void playRoulette() {
        RouletteCoreGameplayEngine core = new RouletteCoreGameplayEngine();
        String numberOfPlayers = RouletteCoreGameplayEngine.rouletteGameEngineSetup();
        ArrayList<RoulettePlayer> roulettePlayers = RouletteCoreGameplayEngine.createRoulettePlayerList(Integer.parseInt(numberOfPlayers));
        boolean currentlyPlayingRound = true;
        while (currentlyPlayingRound) {
            doesPlayerHaveABalance(roulettePlayers);
            doesPlayerWantToExit(roulettePlayers);
            if (roulettePlayers.size() == 0) {
                Console.println("No more players! Leaving roulette table!");
                break;
            }
            RouletteCoreGameplayEngine.gatherPlayerBets(roulettePlayers);
            String spinResult = core.spin();
            checkPlayerBetsForResults(roulettePlayers, spinResult);
        }
    }



    public static void doesPlayerWantToExit (ArrayList<RoulettePlayer> roulettePlayers) {
        for (int count = 0; count < roulettePlayers.size(); count++) {
            Console.println("Does Player #" + roulettePlayers.get(count).getAccount().getId() + " want to exit?");
            if (RouletteCoreGameplayEngine.exitInput()) {
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " has exited.");
                double remainingBalance = roulettePlayers.get(count).getBalance();
                long accountID = roulettePlayers.get(count).getAccount().getId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        double originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        double balanceDifference = remainingBalance - originalBalance;
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
                double remainingBalance = 0;
                long accountID = roulettePlayers.get(count).getAccount().getId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        double originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        double balanceDifference = -originalBalance;
                        Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
                    }
                }
                roulettePlayers.remove(roulettePlayers.get(count));
                count--;
            }
        }
    }



    public static void checkPlayerBetsForResults(ArrayList<RoulettePlayer> roulettePlayers, String spinResult) {
        Console.printDashes();
        Console.println("The ball landed in: " + spinResult);
        for (int count = 0; count < roulettePlayers.size(); count++) {
            RoulettePlayer player = roulettePlayers.get(count);
            Console.printDashes();
            Console.println("Checking bets for Player #" + player.getAccount().getId());
            Console.printDashes();
            ArrayList<RouletteBet> betList = player.getBetList();
            player.getAccount().setAccountBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForInsideBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForColorBetWins(betList, spinResult));
            System.out.print("Player #" + roulettePlayers.get(count).getAccount().getId() + " new balance: $");
            System.out.printf("%,.2f", roulettePlayers.get(count).getBalance());
            Console.printDashes();
            player.resetBetList();

        }


    }

}
