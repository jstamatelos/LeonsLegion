package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;

import java.util.ArrayList;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
class PokerBettingRound {

    private long highBet;
    ArrayList<PokerPlayerBettingRound> playersInRound;
    PokerPlayerBettingRound roundTerminator;

    PokerBettingRound(ArrayList<PokerPlayer> players) {
        playersInRound = new ArrayList<PokerPlayerBettingRound>();
        for (PokerPlayer p : players) {
            playersInRound.add(new PokerPlayerBettingRound(p));
        }
        highBet = 0;
    }

    /*
    Offers each player their options and routs their choice appropriately.
     */
    private void playerChoice(PokerPlayerBettingRound playerBetting) {
        Console.printDashes();
        Console.println(playerBetting.player.getAccount().getAccountHolderName() + "\n" + playerBetting.showHand());
        Console.printDashes();
        String choice = InputHandler.getStringInput("\nYou can FOLD, RAISE, CALL a raise, or if no bets have been made, CHECK.\n");
        boolean loopCondition = true;
        while(loopCondition) {
            loopCondition = false;
            try {
                switch (choice.toUpperCase()) {
                    case "FOLD": // fold
                        playerBetting.folds();
                        break;
                    case "RAISE":
                        long raise = Console.getMoneyInput("\nThe high bet is currently " + Console.moneyToString(highBet) + ". How much would you like to raise above that?");
                        highBet = playerBetting.player.placeBet(highBet + raise);
                        playerBetting.amountIn = highBet;
                        roundTerminator = playerBetting;
                        break;
                    case "CALL":
                        if (highBet == 0) {
                            throw new Exception("\nThere was no raise to call.");
                        }
                        playerBetting.player.placeBet(highBet - playerBetting.amountIn);
                        playerBetting.amountIn = highBet;
                        break;
                    case "CHECK":
                        if (highBet > 0) {
                            throw new Exception("\nYou cannot check.");
                        }
                        break;
                    default:
                        throw new Exception("\nNot a valid choice. Read the instructions again.");
                }
            } catch (Exception e) {
                Console.println(e.getMessage());
                loopCondition = true;
            }
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
    getNextPlayer iterates around the table and skips folded players.
     */
    private PokerPlayerBettingRound getNextPlayer(PokerPlayerBettingRound player) {
        int turnIndex;

        do { //skips folded players
            turnIndex = (playersInRound.indexOf(player) + 1) % playersInRound.size();
        } while(playersInRound.get(turnIndex).folded);

        return playersInRound.get(turnIndex);
    }

    /*
    This method holds the logic that ends a round
    of betting when it becomes the turn of the last
    player who raised.
     */
    void playersMakeBets() {
        PokerPlayerBettingRound player = playersInRound.get(0);
        roundTerminator = playersInRound.get(playersInRound.size() - 1);

        //each iteration of the loop is a turn by a player
        do {
            playerChoice(player);
            player = getNextPlayer(player);
        } while(countFolds() < playersInRound.size() - 1 && player != roundTerminator);
        // end of round
    }

}