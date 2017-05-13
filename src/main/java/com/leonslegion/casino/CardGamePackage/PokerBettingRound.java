package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;

import java.util.ArrayList;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
class PokerBettingRound {

    private double highBet;
    ArrayList<PokerPlayerBettingRound> playersInRound;
    PokerPlayerBettingRound lastToRaise;

    PokerBettingRound(ArrayList<PokerPlayer> players) {
        playersInRound = new ArrayList<PokerPlayerBettingRound>();
        for (PokerPlayer p : players) {
            playersInRound.add(new PokerPlayerBettingRound(p));
        }
        highBet = 0;
    }

    /*
    Offers each player their options and routs their choice appropriately.
    It's also controlling the skipping of folded players, so it's clearly
    doing too much. There's also a tail recursion that needs to be removed.
     */
    private void playerChoice(PokerPlayerBettingRound playerBetting) {
        if(playerBetting.folded) {
            return;
        }
        Console.println(playerBetting.player.getAccount().getAccountHolderName() + "\n" + playerBetting.showHand());
        String choice = InputHandler.getStringInput("You can FOLD, RAISE, CALL a raise, or if no bets have been made, CHECK.\n");
        try {
            switch(choice.toUpperCase()) {
                case "FOLD": // fold
                    playerBetting.folds();
                    break;
                case "RAISE":
                    double raise = InputHandler.getDoubleInput("The high bet is currently " + highBet + ". How much would you like to raise above that?");
                    highBet = playerBetting.player.placeBet(highBet + raise);
                    playerBetting.amountIn = highBet;
                    lastToRaise = playerBetting;
                    break;
                case "CALL":
                    if(highBet == 0) {
                        throw new Exception("There was no raise to call.");
                    }
                    playerBetting.player.placeBet(highBet - playerBetting.amountIn);
                    playerBetting.amountIn = highBet;
                    break;
                case "CHECK":
                    if(highBet > 0) {
                        throw new Exception("You cannot check.");
                    }
                    break;
                default:
                    throw new Exception("Not a valid choice. Read the instructions again.");
                }
        } catch (Exception e) {
            Console.println(e.getMessage());
            playerChoice(playerBetting);
        }
    }

    /*
    An exception will be generated if all the players
    fold consecutively, so this will control for that
    possibility.
     */
    private int countFolds() {
        int count = 0;
        for(PokerPlayerBettingRound player : playersInRound) {
            if(player.folded) {
                count++;
            }
        }
        return count;
    }

    /*
    This method holds the logic that ends a round
    of betting when it becomes the turn of the last
    player who raised.
     */
    void playersMakeBets() {
        PokerPlayerBettingRound player = playersInRound.get(0);
        int turnIndex = 0;
        PokerPlayerBettingRound lastToRaise = player;

        //each iteration of the loop is a turn by a player
        do {
            playerChoice(player);

            turnIndex = (turnIndex + 1) % playersInRound.size();
            player = playersInRound.get(turnIndex);
        } while(countFolds() < playersInRound.size() - 1 && player != lastToRaise);
        // end of round
    }

}