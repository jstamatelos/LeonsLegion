package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class BlackjackGame extends CardGame {
    // Take cards from deck
    // Deal cards
    // Show player initial total and Dealer  initial total
    // If player total = 21 player win , add bet to player to player total
    // If Dealer total = 21 dealer win, remove bet from player
    // Ask player if they want to hit / stay, if player total over 21, remove bet
    // Dealer hit - if 17 or over dealer stay, if dealer over 21, player win - add bet to player total
    // Restart game

    private BlackjackPlayer player;
    private BlackjackDealer dealer;
    private boolean playing;
    private boolean stay;
    private long bet;

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

    private BlackjackDealer createDealer() {
        Account newDealerAccount = Account.AccountFactory.createAccountWithName("Dealer");
        Account.AccountManager.addAccount(newDealerAccount);
        newDealerAccount.setAccountBalance(1000000);
        return new BlackjackDealer(newDealerAccount);
    }

    public void dealerTurn() {

        while (dealer.getHand().getPoints() < 17) {
            Console.println("Dealer hits...");
            dealer.hit(deck);
        }
    }


    private Account getPlayerAccount() {
        return Account.AccountManager.findAccount(player.getAccount().getId());
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
                player = new BlackjackPlayer(acct);
            }

        }

    }


    public void promptPlayAgain() {
        Console.println(" Your new balance is " + player.getBalance());
        String playAgain = InputHandler.getStringInput("Play again?").toLowerCase();
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


    private void selectHand() {
        String h = Console.getStringInput("Which hand?");
    }

    private void getBet() {
        long b = -1;

        while (b == -1) {
            b = Console.getLongInput("How much would you like to bet?");

            System.out.println(b);
            System.out.println(player.getBalance());
            if (b > player.getBalance()) {
                Console.println("Bet exceeds you balance!");
                b = -1;
            }
        }
        bet = b;
    }

    private int endRound() {
        player.showHand();
        dealer.showHand();
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
        Console.println("\u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620");
        System.out.println(bet);
        player.deductBetFromAccount((int)bet);
        System.out.println(player.getBalance());
        promptPlayAgain();
    }

    private void youWin() {
        Console.printDashes();
        Console.println("$ $ $ $ $ $ $ $ $ $ ");
        Console.println("$ $ $ You Win! $ $ $");
        Console.println("$ $ $ $ $ $ $ $ $ $ ");
        Console.printDashes();
        player.addBetToAccount((int) bet);
        promptPlayAgain();
    }

    private void youLose() {
        Console.printDashes();
        Console.println("  \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620");
        Console.println(" \u2620 \u2620 \u2620 You lose! \u2620 \u2620 \u2620");
        Console.println("  \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620 \u2620");
        Console.printDashes();
        player.deductBetFromAccount((int) bet);
        promptPlayAgain();
    }

    private void tie() {
        Console.printDashes();
        Console.println("$ $ $ $ $ $ $ $ $ $ ");
        Console.println("$ $ $ Tie. $ $ $");
        Console.println("$ $ $ $ $ $ $ $ $ $ ");
        Console.printDashes();
        promptPlayAgain();
    }

    private void turn() {
        String action;
        if (player.getHand().splitPossible()) {
            action = Console.getStringInput("Hit, stay, or split?");
        } else {
            action = Console.getStringInput("Hit or stay?");
        }
        handlePlayerAction(action);
    }

    private void handlePlayerAction(String action) {
        switch (action) {
            case "hit":
                if (player.hasSplitHands()) {

                }
                player.hit(deck);
                break;
            case "stay":
                stay = true;
                break;
            case  "split":
                player.split();
                break;
            default:
                turn();
                break;

        }
    }


    public void startBlackJack() {

        if (player.getBalance() < 1) {
            Console.println("You're busted! Goodbye!");
            return;
        }

        initialDeal();
        getBet();

        while (playing) {
            if (player.getHand().getPoints() > 21) {
                bust();
                break;
            }
            player.showHand();
            dealer.cardsShowing();
            turn();

            if (stay == true) {
                stay = false;
                dealerTurn();
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
        Account.AccountManager.addAccount(Account.AccountFactory.createAccountWithName("Cameron"));
        BlackjackGame game = new BlackjackGame();
        game.startBlackJack();
    }


}
