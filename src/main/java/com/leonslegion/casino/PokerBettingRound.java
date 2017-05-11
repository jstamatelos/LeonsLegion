package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
public class PokerBettingRound {

    ArrayList<PokerPlayerBettingRound> stillInGame;

    PokerBettingRound(ArrayList<Player> players) {
        for(Player p : players) {
            stillInGame.add(new PokerPlayerBettingRound(p));
        }
    }

    double playerChoice(PokerPlayerBettingRound player, double highBet) {
        InputHandler ih = new InputHandler();
        while(true) {
            int choice = ih.getIntInput("To fold, enter 0. To call, enter 1. To raise, enter 2.");
            switch(choice) {
                case 0:
                    stillInGame.remove(player);
                    return 0;
                case 1:
                    player.placeBet(highBet - player.amountIn);
                    return 0;
                case 2:
                    double raise = ih.getDoubleInput("How much would you like to raise?");
                    highBet = player.placeBet(highBet + raise);
                    return highBet;
                default:
                    System.out.println("Not a valid choice. Read the instructions again.");
                    break;
            }
        }
    }

    void playersMakeBets() {
        PokerPlayerBettingRound player = stillInGame.get(0);
        int lastRaiseIndex = 0;
        double highBet = 0;
        while(turnIndex != lastRaiseIndex) {
            playerChoice();
        }
    }

}
