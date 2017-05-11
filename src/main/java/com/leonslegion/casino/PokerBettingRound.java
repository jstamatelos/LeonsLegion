package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
public class PokerBettingRound {

    ArrayList<PokerPlayerBettingRound> stillInGame;
    ArrayList<PokerPlayerBettingRound> outOfGame;

    PokerBettingRound(ArrayList<Player> players) {
        for(Player p : players) {
            stillInGame.add(new PokerPlayerBettingRound(p));
        }
    }

    double playerChoice(PokerPlayerBettingRound player, double highBet) {
        InputHandler ih = new InputHandler();
        while(true) {
            int choice = ih.getIntInput("To fold, enter 0. To call, enter 1. To raise, enter 2.");
            try {
                switch(choice) {
                    case 0:
                        stillInGame.remove(player);
                        outOfGame.add(player);
                        return 0;
                    case 1:
                        player.player.placeBet(highBet - player.amountIn);
                        player.amountIn = highBet;
                        return 0;
                    case 2:
                        double raise = ih.getDoubleInput("How much would you like to raise?");
                        highBet = player.player.placeBet(highBet + raise);
                        player.amountIn = highBet;
                        return highBet;
                    default:
                        System.out.println("Not a valid choice. Read the instructions again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void playersMakeBets() {
        PokerPlayerBettingRound player = stillInGame.get(0);
        int turnIndex = 0;
        int lastRaiseIndex = 0;
        double highBet = 0;

        do {
            double amount = playerChoice(player, highBet);
            if(amount > highBet) {
                highBet = amount;
                lastRaiseIndex = turnIndex;
            }
            turnIndex++;
            player = stillInGame.get(turnIndex);
        } while(turnIndex != lastRaiseIndex);
    }

}
