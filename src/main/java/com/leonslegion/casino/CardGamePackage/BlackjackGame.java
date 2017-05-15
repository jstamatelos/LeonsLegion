package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class BlackjackGame extends CardGame {

    private BlackjackPlayer player;
    private BlackjackDealer dealer;
    private boolean playing;
    private boolean stay;
    private long bet;

    private BlackjackDealer createDealer() {

        Account.AccountManager.addAccount("Dealer");
        Account newDealerAccount = Account.AccountManager.findAccount("Dealer");
        newDealerAccount.setAccountBalance(1000000);
        return new BlackjackDealer(newDealerAccount);
    }

    public BlackjackGame() {
        dealer = createDealer();
        loadPlayer();
        playing = true;
        stay = false;
        setHasDealer(true);
    }
    public BlackjackGame(BlackjackPlayer player) {
        this.player = player;
        dealer = createDealer();
        playing = true;
        stay = false;
        setHasDealer(true);
    }

    public void initialDeal() {
        deck.shuffleDeck();
        BlackjackPlayer[] bjplayers = {dealer, player};

        for (BlackjackPlayer bjplayer : bjplayers) {
            BlackjackHand hand = new BlackjackHand();
            buildInitialHand(bjplayer, hand);
            bjplayer.setHand(hand);
        }
    }

    private void buildInitialHand(BlackjackPlayer player, Hand hand) {

        for (int i = 0; i < 2; i++) {
            dealCard(hand);
        }
        player.setHand(hand);
    }

    private void dealCard(Hand hand) {
        Card card = deck.dealCard();
        hand.addCard(card);
    }

    private void loadPlayer() {

        long accountId = -1;

        while (accountId == -1) {
            accountId = Console.getLongInput("Enter ID number: ");
            Account acct = Account.AccountManager.findAccount(accountId);
            if (acct == null) {
                Console.println("Not a valid account id.");
                accountId = -1;
            } else {
                printRules();
                player = new BlackjackPlayer(acct);
            }
        }
    }

    public void promptPlayAgain() {
        Console.print(" Your new balance is ");
        Console.printMoney(player.getBalance());
        Console.println("\n");
        String playAgain = Console.getStringInput("Play again?").toLowerCase();
        switch (playAgain) {
            case "yes":
                startBlackJack();
                break;

            case "no":
                playing = false;
                break;

            default:
                promptPlayAgain();
                break;
        }
    }

    private void getBet() {
        long b = -1;

        while (b == -1) {
            b = Console.getMoneyInput("How much would you like to bet?");
            if (b > player.getBalance()) {
                Console.println("Bet exceeds your balance!");
                b = -1;
            }
        }
        bet = b;
    }

    private int endRound() {
        player.showHand();
        Console.println("\n");
        dealer.showHand();
        Console.println("\n");
        return player.getHand().compareTo(dealer.getHand());

    }

    private void bust() {
        Console.printDashes();
        player.showHand();
        Console.printDashes();
        dealer.showHand();
        Console.printDashes();
        Console.println("\u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620");
        Console.println(" \u2620 \u2620 \u2620 Bust! \u2620 \u2620 \u2620");
        Console.println("\u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \n");

        player.deductBetFromAccount((int) bet);
        promptPlayAgain();
    }

    private void youWin() {
        Console.printDashes();
        Console.println("\n $ $ $ $ $ $ $ $ $ $ ");
        Console.println("$ $ $ You Win! $ $ $");
        Console.println("$ $ $ $ $ $ $ $ $ $ \n");
        Console.printDashes();
        player.addBetToAccount((int) bet);
        promptPlayAgain();
    }

    private void youLose() {
        Console.printDashes();
        Console.println(" \n \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620");
        Console.println(" \u2620 \u2620 \u2620 You lose! \u2620 \u2620 \u2620");
        Console.println("  \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \n");
        Console.printDashes();
        player.deductBetFromAccount((int) bet);
        promptPlayAgain();
    }

    private void tie() {
        Console.printDashes();
        Console.println(" $ $ $ $ $ $ $ $ $  ");
        Console.println("$ $ $ Push. $ $ $ $");
        Console.println(" $ $ $ $ $ $ $ $ $  \n");
        Console.printDashes();
        promptPlayAgain();
    }

    private void hitBlackjack() {
        Console.printDashes();
        Console.println("\n $ $ $ $ $ $ $ $ $ $ ");
        Console.println("$ $ $ Blackjack! $ $ $");
        Console.println("$ $ $ $ $ $ $ $ $ $ \n");
        Console.printDashes();
        player.showHand();
        bet *= .75;
        player.addBetToAccount((int) bet);
        promptPlayAgain();
    }

    private void turn() {
        String action;
        Console.println("\n");
        Console.printDashes();
        if (player.getHand().splitPossible()) {
            action = Console.getStringInput("\n\nHit, stay, or split?");
        } else {
            action = Console.getStringInput("\n\nHit or stay?");
        }

        handlePlayerAction(action);
    }

    private void handleSplit() {
        player.split();
        player.hitSplitHand(1, deck);
        player.hitSplitHand(2, deck);

        player.showHand();
        for (int i = 0; i < player.getSplitHands().size(); i++) {
            stay = false;
            while (stay == false) {
                splitTurn(i+1);
                if (player.getSplitHands().get(0).getPoints() > 21 || player.getSplitHands().get(1).getPoints() > 21) {
                    Console.println("Hand " + (i+1) +  "busts");
                    stay = true;
                }
            }
        }

        dealer.takeTurn(deck);
        dealer.showHand();

        int handNum = 1;
        for (BlackjackHand hand : player.getSplitHands()) {
            evaluateSplits(hand, handNum);
            handNum += 1;
        }

        stay = false;

        //player.showHand();
        player.removeSplitHand();
        promptPlayAgain();
    }

    private void evaluateSplits(BlackjackHand hand, int handNum) {
        if (hand.compareTo(dealer.getHand()) == -1) {
            Console.printDashes();
            Console.println("Hand " + handNum + " wins!");
            Console.printDashes();
            player.addBetToAccount((int) bet);
        } else if (hand.compareTo(dealer.getHand()) == 1) {
            Console.printDashes();
            Console.println("Hand " + handNum + " loses!");
            Console.printDashes();
            player.deductBetFromAccount((int) bet);

            } else {
            Console.printDashes();
            Console.println("Hand " + handNum + " is a tie.");
            Console.printDashes();
        }
    }

    private void splitTurn(int hand) {
        String action = Console.getStringInput("Hit or stay on hand " + hand + "?");
        handleSplitAction(action, hand);
        player.showHand();

    }

    private void handleSplitAction(String action, int hand) {
        switch (action) {
            case "hit":
                player.hitSplitHand(hand, deck);
                break;
            case "stay":
                stay = true;
                break;
            default:
                splitTurn(hand);
                break;
        }
    }

    private void handlePlayerAction(String action) {
        switch (action) {
            case "hit":
                player.hit(deck);
                break;
            case "stay":
                stay = true;
                break;
            case  "split":
                if (player.getHand().splitPossible()) {
                    handleSplit();
                } else {
                    Console.println("Split not possible!");
                    turn();
                }
                break;
            default:
                turn();
                break;
        }
    }

    private void printRules() {
        Console.printDashes();
        Console.println("Rules: ");
        Console.println("Dealer stays on soft 17.");
        Console.println("Player may split once on initial pair only.");
        Console.println("Player gets Blackjack on initial deal whether");
        Console.println("the dealer shows an Ace or not.");
        Console.println("Blackjack pays 1.5x, other wins pay 2x. \n");
        Console.println("Good luck!");
        Console.printDashes();


    }


    public void startBlackJack() {

        if (player.getBalance() < 1) {
            Console.println("You're busted! Goodbye!");
            playing = false;
            return;
        }

        initialDeal();
        getBet();

        if (player.getHand().hasBlackjack()) {
            hitBlackjack();
        }

        while (playing) {
            if (player.getHand().getPoints() > 21) {
                bust();
                break;
            }

            if (!player.hasSplitHands()) {
                player.showHand();
                Console.println("\n");
                dealer.cardsShowing();
                turn();
            }

            if (stay == true) {
                stay = false;
                dealer.takeTurn(deck);
                int result = endRound();
                if (result == -1) {
                    youWin();
                } else if (result == 1) {
                    youLose();
                } else {
                    tie();
                }
            }
        }
    }

    public static void main(String[] args) {
        Account.AccountManager.addAccount("Cameron");
        BlackjackGame game = new BlackjackGame();
        game.startBlackJack();
    }
}
