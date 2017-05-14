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
/*
    public static long getPlayerIDInput() {
        Console.println("");
        return getLongInput("Please enter your ID.");
    }




    public static ArrayList<RoulettePlayer> createRoulettePlayerList(long numberOfPlayers) {
        ArrayList<RoulettePlayer> players = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < numberOfPlayers; playerIndex++) {
            RoulettePlayer newPlayer = addRoulettePlayer(players);
            players.add(newPlayer);
        }
        return players;
    }*/
}