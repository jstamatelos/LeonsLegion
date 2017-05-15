package com.leonslegion.casino.RoulettePackage;

import java.util.*;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by markbrown on 5/13/17.
 */

public class RouletteInputOutput {


    public static long getNumberOfPlayers(InputAsker inputAsker) {
        Console.println("Type '0' to return to menu.");
        long numberOfAttempts = 2;
        RoulettePrint.printNumberOfAttemptsRemaining(numberOfAttempts);
        String input = inputAsker.askForInput("Please enter integer number of players. 2 is max.");
        while (!input.equals("0") && !input.equals("1") && !input.equals("2") && numberOfAttempts > 0) {
            numberOfAttempts--;
            RoulettePrint.printNumberOfAttemptsRemaining(numberOfAttempts);
            input = inputAsker.askForInput("Not a valid input!");
            if (!input.equals("0") && !input.equals("1") && !input.equals("2") && numberOfAttempts == 1) {
                return 0;
            }
        }
        return Long.parseLong(input);
    }


    public static ArrayList<RoulettePlayer> createRoulettePlayerList(long numberOfPlayers) {
        ArrayList<RoulettePlayer> players = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            long playerID = getPlayerID(new InputAsker(System.in, System.out));
            if (checkIfPlayerIDExists(playerID) && !checkIfPlayerAlreadyRegistered(players, playerID)) {
                Account roulettePlayerAccount = Account.AccountManager.findAccount(playerID);
                RoulettePlayer newPlayer = new RoulettePlayer(roulettePlayerAccount, new ArrayList<RouletteBet>());
                players.add(newPlayer);
            }
        }
        return players;
    }


    public static long getPlayerID(InputAsker inputAsker) {
        Console.println("");
        long numberOfAttempts = 2;
        RoulettePrint.printNumberOfAttemptsRemaining(numberOfAttempts);
        String input = inputAsker.askForInput("Please enter your ID. Fractional components will be ignored.");
        while (!NumberUtils.isParsable(input) && numberOfAttempts > 0) {
            numberOfAttempts--;
            RoulettePrint.printNumberOfAttemptsRemaining(numberOfAttempts);
            input = inputAsker.askForInput("Not a valid input!");
            if (!NumberUtils.isParsable(input) && numberOfAttempts == 1) {
                RoulettePrint.printAttemptsExceeded();
                return (long) -1;
            }
        }
        return Long.parseLong(input);
    }


    public static boolean checkIfPlayerIDExists(long ID) {
        Account roulettePlayerAccount = Account.AccountManager.findAccount(ID);
        if (roulettePlayerAccount == null) {
            RoulettePrint.printAccountNotFoundMessage();
            return false;
        } else {
            return true;
        }
    }


    public static boolean checkIfPlayerAlreadyRegistered(ArrayList<RoulettePlayer> players, long ID) {
        Account roulettePlayerAccount = Account.AccountManager.findAccount(ID);
        if (players.size() == 1 && roulettePlayerAccount.getId() == players.get(0).getAccount().getId()) {
            RoulettePrint.printAccountAlreadyLoaded();
            return false;
        } else {
            return true;
        }
    }
}

