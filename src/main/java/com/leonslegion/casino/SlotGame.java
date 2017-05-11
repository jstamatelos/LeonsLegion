package com.leonslegion.casino;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class SlotGame {

    public static void main(String[] args) {
        Account account = new Account("Leon");

        SlotPlayer sp = new SlotPlayer(account);
        playSlots(sp);
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
            if(sp.placeBet() == false) {
                break;
            }
            SlotMachine machine = new SlotMachine(sp);
            machine.pullLever();
            System.out.printf("Your balance is now: $%.2f\n", sp.getBalance());
        }
    }


    // Player bets
    // Player spins
    // Depending on outcome of spin, player gets payout or loses bet amount
    // Repeat until rich or poor

}
