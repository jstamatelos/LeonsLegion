package com.leonslegion.casino;
import com.leonslegion.casino.CardGamePackage.*;
import com.leonslegion.casino.AccountPackage.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jarrydstamatelos on 5/10/17.
 */

public class WarGameTest {

    @Test
    public void playerDeckShuffleTest(){
        Deck playerDeck = new Deck();
        Deck unshuffledDeck= new Deck();

        playerDeck.shuffleDeck();

        Assert.assertNotEquals(playerDeck, unshuffledDeck);

    }
    @Test
    public void dealerDeckShuffleTest(){
        Deck dealerDeck = new Deck();
        Deck unshuffledDeck= new Deck();

        dealerDeck.shuffleDeck();

        Assert.assertNotEquals(dealerDeck, unshuffledDeck);

    }

    @Test
    public  void setplayerCardTest(){

        Deck playerDeck = new Deck();
        Card actual= new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        String expected = playerDeck.dealCard().toString();

        Assert.assertEquals(actual.toString(),expected );

    }



    @Test
    public void createWarPlayer() {
        Account account = new Account();
        WarPlayer newWarPlayer = new WarPlayer(account);

        Assert.assertNotNull(newWarPlayer);
    }

    @Test
    public void placeBet()  {

        Account account = new Account();
        WarPlayer player = new WarPlayer(account);

        try {
            player.placeBet(100);
        } catch (Exception e){
            Console.print("This should never get hit.");
        }


        double actual = player.getBalance();
        double expected = 900;


        Assert.assertEquals(actual,expected,0.00001);

    }


    @Test
    public void testDealerShowCard() {

        Deck dealerDeck = new Deck();
        Card dealerCard = dealerDeck.dealCard();
        Card expected = dealerCard;
        Card actual = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        Assert.assertEquals(actual.toString(),expected.toString() );

    }

    @Test
    public void testPickHigherValue()  {


        CardComparator comp = new CardComparator();

        Card card0 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);


        Assert.assertTrue("Card zero is has greater point value than card one",
                card0.getPointValue() > card1.getPointValue());


    }

    @Test
    public void testDealerAndPlayerShowSameCard() {

        int expected = 0;

        CardComparator comp = new CardComparator();
        Card card0 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        int actual = comp.compare(card0, card1);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testDealerAndPlayerShowDifferentCard() {

        int expected = 0;

        CardComparator comp = new CardComparator();
        Card card0 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        int actual = comp.compare(card0, card1);

        Assert.assertNotEquals("These cards should not be same",expected, actual);
    }


}

