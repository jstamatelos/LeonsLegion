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



    // Fully Tested, including:
    // testGetNumberOfPlayersInputCanBe0, testGetNumberOfPlayersInputCanBeInvalidOnce,
    // testGetNumberOfPlayersInputCanBeInvalidTwice
    public static long getNumberOfPlayers(InputAsker inputAsker) {
        Console.println("Type '0' to return to menu.");
        String input = inputAsker.askForInput("Please enter integer number of players. 2 is max.");
        while (!input.equals("0") && !input.equals("1") && !input.equals("2")) {
            input = inputAsker.askForInput("Not a valid input!");
        }
        return Long.parseLong(input);
    }



    // Fully Tested, including:

    public static ArrayList<RoulettePlayer> createRoulettePlayerList(long numberOfPlayers) {
        ArrayList<RoulettePlayer> players = new ArrayList<>();
        while (players.size() != numberOfPlayers) {
            long playerID = getPlayerID(new InputAsker(System.in, System.out));
            boolean doesIDExist = checkIfPlayerIDExists(playerID);
            if (doesIDExist) {
                boolean isIDAlreadyRegistered = checkIfPlayerAlreadyRegistered(players, playerID);
                if (!isIDAlreadyRegistered) {
                    Account roulettePlayerAccount = Account.AccountManager.findAccount(playerID);
                    RoulettePlayer newPlayer = new RoulettePlayer(roulettePlayerAccount, new ArrayList<RouletteBet>());
                    players.add(newPlayer);
                }
                else {
                    RoulettePrint.printAccountAlreadyLoaded();
                }
            }
            else {
                RoulettePrint.printAccountNotFoundMessage();
            }
        }
        return players;
    }



    // Fully Tested, including:
    // testGetPlayerIDInput, testGetPlayerIDCanBeInvalidOnce, testGetPlayerIDCanBeNegativeOnce
    // testPlayerIDAsDouble
    public static long getPlayerID(InputAsker inputAsker) {
        Console.println("");
        String input = inputAsker.askForInput("Please enter your ID. Fractional components will be ignored.");
        while (true) {
            if (!NumberUtils.isParsable(input)) {
                input = inputAsker.askForInput("Not a valid input!");
                continue;
            }
            if (Double.parseDouble(input) < 0) {
                input = inputAsker.askForInput("Not a valid input!");
                continue;
            }
            double inputAsDouble = Double.parseDouble(input);
            long inputAsLong = (long) inputAsDouble;
            return inputAsLong;
        }
    }



    // Fully Tested, including:
    // testIfPlayerIDThatExistsIsFound, testIfPlayerIDThatDoesNotExistIsNotFound
    public static boolean checkIfPlayerIDExists(long ID) {
        Account roulettePlayerAccount = Account.AccountManager.findAccount(ID);
        if (roulettePlayerAccount == null) {
            return false;
        } else {
            return true;
        }
    }



    // Fully Tested, including:
    // testThatAccountNotRegisteredIsAdded, testThatRegisteredAccountIsNotAdded
    public static boolean checkIfPlayerAlreadyRegistered(ArrayList<RoulettePlayer> players, long ID) {
        Account roulettePlayerAccount = Account.AccountManager.findAccount(ID);
        if (players.size() == 1 && roulettePlayerAccount.getId() == players.get(0).getAccount().getId()) {
            RoulettePrint.printAccountAlreadyLoaded();
            return true;
        } else {
            return false;
        }
    }
}

