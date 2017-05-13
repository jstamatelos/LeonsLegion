package com.leonslegion.casino.SlotPackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class SlotGame {


    public static void playSlots() {
        SlotPlayer newPlayer = createSlotPlayer();
        playSlots(newPlayer);
        adjustBalance(newPlayer);
    }



    public static void playSlots(SlotPlayer sp) {
        String response = "y";
        Console.println("Welcome to the slot machine!");
        Console.println("It will be $5.00 to play.");
        while (!response.equalsIgnoreCase("n")) {
            response = InputHandler.getStringInput("Press Y to pull the lever or N to quit.").toLowerCase();
            if (response.equalsIgnoreCase("n")) {
                break;
            }
            if(!sp.placeBet()) {
                break;
            }
            SlotMachine machine = new SlotMachine(sp);
            machine.pullLever();
            System.out.printf("Your balance is now: $%.2f\n", sp.getBalance());
        }
    }

    public static SlotPlayer createSlotPlayer() {
        while (true) {
            String slotPlayerID = InputHandler.getStringInput("Please enter your ID.");
            if (!NumberUtils.isParsable(slotPlayerID)) {
                Console.println("Not a valid ID");
                continue;
            }
            Account slotPlayerAccount = Account.AccountManager.findAccount(Long.parseLong(slotPlayerID));
            if (slotPlayerAccount == null) {
                Console.println("ID not found!");
                continue;
            }
            Console.printDashes();
            Console.println("ID accepted!");
            Console.printDashes();
            SlotPlayer newSlotPlayer = new SlotPlayer(slotPlayerAccount);
            return newSlotPlayer;
        }
    }


    public static void adjustBalance(SlotPlayer newPlayer) {
        Console.println("Player has exited.");
        double remainingBalance = newPlayer.getBalance();
        long accountID = newPlayer.getAccount().getId();
        for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
            if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                double originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                double balanceDifference = remainingBalance - originalBalance;
                Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
            }
        }
    }

    // Player bets
    // Player spins
    // Depending on outcome of spin, player gets payout or loses bet amount
    // Repeat until rich or poor

}
