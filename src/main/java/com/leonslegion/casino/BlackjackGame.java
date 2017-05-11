package com.leonslegion.casino;

import java.util.ArrayList;

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
    private double bet;

    public BlackjackPlayer createDealer() {
        return new BlackjackPlayer(1000000000, 0) {
            @Override
            public double placeBet(double d) {
                // do nothing
                return -1;
            }
        };
    }

    public BlackjackGame() {
        dealer = createDealer();
        playing = true;
    }

 /*   @Override
    public ArrayList<BlackjackPlayer> getPlayers() {
        return ((ArrayList<BlackjackPlayer>) super.getPlayers());
    }
*/

    public String promptTurnAction() {

        BlackjackHand hand = (BlackjackHand) player.getHand();
        BlackjackHand dealerHand = (BlackjackHand) dealer.getHand();
        String userPrompt = buildUserPromptString(hand, dealerHand);

        return InputHandler.getStringInput(userPrompt);

    }

    private void handlePlayerAction(String action) {
        switch (action) {
            case "hit":
                dealCard(player.getHand());
                break;
            case "stay":
                break;
            default:
                promptTurnAction();

        }

    }


    private void deductBetFromAccount() {
        getPlayerAccount().setAccountBalance(bet *= -1);
    }

    private void addBetToAccount() {
        getPlayerAccount().setAccountBalance(bet *= 1.5);
    }

    private double returnBalance() {
        return getPlayerAccount().getAccountBalance();
    }

    private Account getPlayerAccount() {
        return AccountManager.findAccount(player.getAccountId());
    }


    public String buildUserPromptString(BlackjackHand hand, BlackjackHand dealerHand) {

        StringBuilder sb = buildHandString(hand, "Your ");
        StringBuilder dealerSb = buildHandString(dealerHand, "The Dealer's ");
        sb.append(dealerSb.toString());
        return buildUserActionPrompt(hand, dealerHand, sb);

    }


    private String buildUserActionPrompt(BlackjackHand playerHand, BlackjackHand dealerHand, StringBuilder sb) {
        switch (compareHands(playerHand, dealerHand)) {
            case "loser":
                sb.append(" you lose!");
                sb.append(returnBalance());
                break;

            case "winner":
                sb.append(" you win!");
                break;

            case "":
                sb.append(" hit or stay?");
                break;
        }
        return sb.toString();
    }

    public String compareHands(BlackjackHand playerHand, BlackjackHand dealerHand) {
        int dealerHandValue = dealerHand.evaluateHand();
        int playerHandValue = playerHand.evaluateHand();
        if (dealerHandValue == 21 || playerHandValue > 21) {
            return "loser";
        } else if (dealerHandValue  > 21 || playerHandValue== 21) {
            return "winner";
        } else {
            return "";
        }
    }

    private String playerLost(StringBuilder sb) {
        sb.append("You Lose!");
        sb.append(returnBalance());
        deductBetFromAccount();
        return sb.toString();
    }

    private String playerWon(StringBuilder sb) {
        sb.append("You won!");
        addBetToAccount();
        return sb.toString();
    }


    private StringBuilder buildHandString(BlackjackHand hand, String playerIdentifier) {

        int points = hand.evaluateHand();
        StringBuilder sb = new StringBuilder();
        sb.append(playerIdentifier);
        sb.append(" hand: ");
        for (Card card : hand.getHand()) {
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
        BlackjackHand playerHand = new BlackjackHand();
        BlackjackHand dealerHand = new BlackjackHand();
        buildInitialHand(player, playerHand);
        buildInitialHand(dealer, dealerHand);
        player.setHand(playerHand);
        dealer.setHand(dealerHand);
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

    private Card dealCard() {
        return deck.dealCard();
    }

    private void loadPlayer() {
        long accountId = Long.parseLong(InputHandler.getStringInput("Enter ID number: "));
        Account acct = AccountManager.findAccount(accountId);
        double balance = AccountManager.getBalance(acct);
        player = new BlackjackPlayer(balance, accountId);
    }

    private void placeBet() {

        bet = InputHandler.getDoubleInput("How much would you like to bet?");
    }

    public void play() {

        loadPlayer();
        placeBet();
        initialDeal();

        while (playing) {
            String playerAction = promptTurnAction();
            handlePlayerAction(playerAction);
        }

    }

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++){
                AccountManager.addAccount(AccountFactory.createAccountWithName("Guest" + i));
        }

        BlackjackGame b = new BlackjackGame();
        b.play();
    }


}
