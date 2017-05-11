package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

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
        System.out.println("Welcome to the slot machine!");
        System.out.println("It will be $5.00 to play.");
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
                System.out.println("Not a valid ID");
                continue;
            }
            Account slotPlayerAccount = AccountManager.findAccount(Long.parseLong(slotPlayerID));
            if (slotPlayerAccount == null) {
                System.out.println("ID not found!");
                continue;
            }
            System.out.println();
            System.out.println("ID accepted!");
            System.out.println();
            SlotPlayer newSlotPlayer = new SlotPlayer(slotPlayerAccount.getAccountBalance(), slotPlayerAccount.getId());
            return newSlotPlayer;
        }
    }


    public static void adjustBalance(SlotPlayer newPlayer) {
        System.out.println("Player has exited.");
        double remainingBalance = newPlayer.getBalance();
        long accountID = newPlayer.getAccountId();
        for (int account = 0; account < AccountManager.getAccounts().size(); account++) {
            if (AccountManager.getAccounts().get(account).getId() == accountID) {
                double originalBalance = AccountManager.getAccounts().get(account).getAccountBalance();
                double balanceDifference = remainingBalance - originalBalance;
                AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
            }
        }
    }

    // Player bets
    // Player spins
    // Depending on outcome of spin, player gets payout or loses bet amount
    // Repeat until rich or poor

}
