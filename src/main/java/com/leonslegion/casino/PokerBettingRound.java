package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
class PokerBettingRound {

    private double highBet;
    ArrayList<PokerPlayerBettingRound> playersStillInGame;
    ArrayList<PokerPlayerBettingRound> playersOutOfGame;

    PokerBettingRound(ArrayList<PokerPlayer> players) {
        playersStillInGame = new ArrayList<PokerPlayerBettingRound>();
        playersOutOfGame = new ArrayList<PokerPlayerBettingRound>();
        for (PokerPlayer p : players) {
            playersStillInGame.add(new PokerPlayerBettingRound(p));
        }
    }

    /*
    Offers each player their options and routs their choice appropriately.
     */
    private double playerChoice(PokerPlayerBettingRound player) {
        System.out.println(player.showHand());
        while(true) {
            String choice = InputHandler.getStringInput("You can FOLD, CALL, RAISE, or if not bets have been made, CHECK.");
            try {
                switch(choice) {
                    case "FOLD": // fold
                        playersStillInGame.remove(player);
                        playersOutOfGame.add(player);
                        return 0;
                    case "CALL":
                        player.player.placeBet(highBet - player.amountIn);
                        player.amountIn = highBet;
                        return 0;
                    case "RAISE":
                        double raise = InputHandler.getDoubleInput("How much would you like to raise?");
                        highBet = player.player.placeBet(highBet + raise);
                        player.amountIn = highBet;
                        return highBet;
                    case "CHECK":
                        if(highBet == 0) {
                            throw new Exception("You cannot check.");
                        }
                        break;
                    default:
                        System.out.println("Not a valid choice. Read the instructions again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    This method holds the logic that ends a round
    of betting when it becomes the turn of the last
    player who raised.
     */
    void playersMakeBets() {
        PokerPlayerBettingRound player = playersStillInGame.get(0);
        int turnIndex = 0;
        int lastRaiseIndex = 0;
        int currentSize = playersStillInGame.size();
        highBet = 0;

        do { // an iteration represents a single move for
             // a player
            double amount = playerChoice(player);
            if(amount > highBet) {
                highBet = amount;
                lastRaiseIndex = turnIndex;
            }

            //this condition refers to a fold
            if(currentSize > playersStillInGame.size()) {
                turnIndex--;
                currentSize--;
            }

            turnIndex = (turnIndex + 1) % currentSize;
            player = playersStillInGame.get(turnIndex);
        } while(turnIndex != lastRaiseIndex);
        // end of round
    }

}