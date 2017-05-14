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
    private BlackjackPlayer dealer;
    private boolean playing;
    private long bet;

    public BlackjackPlayer createDealer() {
        Account newDealerAccount = Account.AccountFactory.createAccountWithName("Dealer");
        Account.AccountManager.addAccount(newDealerAccount);
        newDealerAccount.setAccountBalance(100000000);

        return new BlackjackPlayer(newDealerAccount) {
            @Override
            public long placeBet(long d) {
                // do nothing
                return -1;
            }
        };
    }

    public BlackjackGame(BlackjackPlayer player, BlackjackPlayer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    private String dealerAction() {
        BlackjackHand dealerHand = (BlackjackHand) dealer.getHand();
        if (dealerHand.sumHand() <= 17) {
            Console.println("Dealer Hits...");
            return "hit";
        }
        else {
            Console.println("Dealer stays with " + dealerHand.sumHand());
            return "stay";
        }
    }

    public BlackjackGame() {
        dealer = createDealer();
        playing = true;
    }

    public String promptTurnAction() {

        BlackjackHand hand = (BlackjackHand) player.getHand();
        BlackjackHand dealerHand = (BlackjackHand) dealer.getHand();
        String userPrompt = buildUserPromptString(hand, dealerHand);

        return InputHandler.getStringInput(userPrompt);

    }

    private void handleTurn(BlackjackPlayer player, String action) {
        switch (action) {
            case "hit":
                dealCard(player.getHand());
                if (Account.AccountManager.findAccount(player.getAccount().getId()).getAccountHolderName().equalsIgnoreCase("dealer")) {
                    Console.println("Dealer's hand: ");
                } else {
                    Console.println("Your hand: ");
                }
                BlackjackHand playerHand = (BlackjackHand)player.getHand();
                Console.println(playerHand + " total = " + playerHand.sumHand());
                break;
            case "stay":
                handleStayAction(player);
                break;
            default:
                promptTurnAction();
                break;
        }

    }

    private void handleStayAction(BlackjackPlayer player) {

        if (player.equals(dealer)) {

            while (dealerAction().equals("hit")) {
                handleTurn(dealer, dealerAction());
            }
            compareHands();
        }
    }


    private void deductBetFromAccount() {
        getPlayerAccount().setAccountBalance(bet *= -1);
    }

    private void addBetToAccount() {
        getPlayerAccount().setAccountBalance(bet *= 1.5);
    }

    private long returnBalance() {
        return getPlayerAccount().getAccountBalance();
    }

    private Account getPlayerAccount() {
        return Account.AccountManager.findAccount(player.getAccount().getId());
    }


    public String buildUserPromptString(BlackjackHand hand, BlackjackHand dealerHand) {

        StringBuilder sb = buildHandString(hand, "Your ");
        StringBuilder dealerSb = buildHandString(dealerHand, "The Dealer's ");
        sb.append(dealerSb.toString());
        return buildUserActionPrompt(hand, dealerHand, sb);

    }


    private String buildUserActionPrompt(BlackjackHand playerHand, BlackjackHand dealerHand, StringBuilder sb) {
        switch (check21(playerHand, dealerHand)) {
            case "loser":
                sb.append(" you lose!");
                deductBetFromAccount();
                sb.append("\n New balance: ");
                sb.append(returnBalance());
                playing = false;
                //promptPlayAgain();
                break;

            case "winner":
                sb.append(" you win!");
                Console.println(sb.toString());
                addBetToAccount();
                playing = false;
                //promptPlayAgain();
                break;

            case "":
                sb.append(" hit or stay?");
                break;
        }
        return sb.toString();
    }

    public String check21(BlackjackHand playerHand, BlackjackHand dealerHand) {
        int dealerHandValue = dealerHand.sumHand();
        int playerHandValue = playerHand.sumHand();
        if (dealerHandValue == 21 || playerHandValue > 21) {
            deductBetFromAccount();
            return "loser";
        } else if (dealerHandValue  > 21 || playerHandValue == 21) {
            addBetToAccount();
            return "winner";
        } else {
            return "";
        }
    }

    public void compareHands() {
        BlackjackHand playerHand = (BlackjackHand) player.getHand();
        BlackjackHand dealerHand = (BlackjackHand) dealer.getHand();
        String result = check21(playerHand, dealerHand);

        if (result.equals("") || result.equals("winner")) {
            if (playerHand.sumHand() > dealerHand.sumHand()) {
                addBetToAccount();
                Console.println("You Win!");
            } else {
                deductBetFromAccount();
                Console.println("You lose!");
            }
            //promptPlayAgain();
        }
    }

    private StringBuilder buildHandString(BlackjackHand hand, String playerIdentifier) {

        int points = hand.sumHand();
        StringBuilder sb = new StringBuilder();
        sb.append(playerIdentifier);
        sb.append(" hand: ");
        for (Card card : hand.getCards()) {
            sb.append(card.getRank());
            sb.append(" of ");
            sb.append(card.getSuit());
            sb.append("; ");
        }
        sb.append("is worth ");
        sb.append(points);
        sb.append(" points. ");
        sb.append("\n");
        return sb;
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
        long accountId = Long.parseLong(InputHandler.getStringInput("Enter ID number: "));
        Account acct = Account.AccountManager.findAccount(accountId);
        long balance = Account.AccountManager.getBalance(acct);
        player = new BlackjackPlayer(acct);
    }

    private void placeBet() {
        bet = Console.getMoneyInput("How much would you like to bet?");
    }

    public void play() {

        if (player == null) {
            loadPlayer();
        }

        placeBet();
        initialDeal();

        while (playing == true) {
            String playerAction = promptTurnAction();

            handleTurn(player, playerAction);
            handleTurn(dealer, dealerAction());
        }
        Console.println("play loop iteration completed");
        promptPlayAgain();

    }

    public void promptPlayAgain() {
        Console.println(" Your new balance is " + returnBalance());
        String playAgain = InputHandler.getStringInput("Play again?").toLowerCase();
        switch (playAgain) {
            case "yes":
                //play();
                break;

            case "no":
                playing = false;
                break;

            default:
                promptPlayAgain();
                break;
        }
    }

    public static void startBlackJack() {




        BlackjackGame b = new BlackjackGame();
        b.play();
    }


}
