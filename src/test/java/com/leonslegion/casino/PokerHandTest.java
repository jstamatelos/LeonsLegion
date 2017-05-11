package com.leonslegion.casino;

import org.junit.*;


public class PokerHandTest {

    @Test
    public void compareHandTypesNegativeTest() {
        PokerHand hand1 = new PokerHand();
        hand1.setHandType(PokerHand.HandType.FLUSH);
        PokerHand hand2 = new PokerHand();
        hand2.setHandType(PokerHand.HandType.FOUROFAKIND);

        int result = hand1.compareTo(hand2);

        Assert.assertTrue(result < 0);
    }

    @Test
    public void compareHandTypesPositiveTest() {
        PokerHand hand1 = new PokerHand();
        hand1.setHandType(PokerHand.HandType.FLUSH);
        PokerHand hand2 = new PokerHand();
        hand2.setHandType(PokerHand.HandType.STRAIGHT);

        int result = hand1.compareTo(hand2);

        Assert.assertTrue(result > 0);
    }

    @Test
    public void compareHandTypesEqualTest() {
        PokerHand hand1 = new PokerHand();
        hand1.setHandType(PokerHand.HandType.FOUROFAKIND);
        PokerHand hand2 = new PokerHand();
        hand2.setHandType(PokerHand.HandType.FOUROFAKIND);

        int result = hand1.compareTo(hand2);

        Assert.assertTrue(result == 0);
    }

    @Test
    public void hasFlushTrueTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));

        boolean result = hand.hasFlush();

        Assert.assertTrue(result);
    }

    @Test
    public void hasFlushFalseTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));

        boolean result = hand.hasFlush();

        Assert.assertFalse(result);
    }

    @Test
    public void hasStraightTrueTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));

        boolean result = hand.hasStraight();

        Assert.assertTrue(result);
    }

    @Test
    public void hasStraightFalseTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));

        boolean result = hand.hasStraight();

        Assert.assertFalse(result);
    }

    @Test
    public void countRankTest0() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));

        int result = hand.countRank(Card.Rank.EIGHT);

        Assert.assertTrue(result == 0);
    }

    @Test
    public void countRankTest1() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));

        int result = hand.countRank(Card.Rank.THREE);

        Assert.assertTrue(result == 1);
    }

    @Test
    public void countRankTest3() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

        int result = hand.countRank(Card.Rank.TEN);

        Assert.assertTrue(result == 3);
    }

    @Test
    public void numberOfRanksPresentTwoTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

        int result = hand.numberOfRanksPresent();

        Assert.assertTrue(result == 2);
    }

    @Test
    public void numberOfRanksPresentThreeTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

        int result = hand.numberOfRanksPresent();

        Assert.assertTrue(result == 3);
    }

    @Test
    public void numberOfRanksPresentFourTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        int result = hand.numberOfRanksPresent();

        Assert.assertTrue(result == 4);
    }

    @Test
    public void numberOfRanksPresentFiveTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

        int result = hand.numberOfRanksPresent();

        Assert.assertTrue(result == 5);
    }

    /*
    The tests below aren't exactly unit tests.
     */
    
    @Test
    public void fiveRankHandChooserHighCardTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.fiveRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.HIGHCARD);
    }

    @Test
    public void fiveRankHandChooserFlushTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));

        PokerHand.HandType result = hand.fiveRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.FLUSH);
    }

    @Test
    public void fiveRankHandChooserStraightTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.NINE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.fiveRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.STRAIGHT);
    }

    @Test
    public void fourRankHandChooserTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.fourRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.PAIR);
    }

    @Test
    public void threeRankHandChooserThreeOfAKindTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.threeRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.THREEOFAKIND);
    }

    @Test
    public void threeRankHandChooserTwoPairTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.threeRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.TWOPAIR);
    }

    @Test
    public void twoRankHandChooserFourOfAKindTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.twoRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.FOUROFAKIND);
    }

    @Test
    public void twoRankHandChooserFullHouseTest() {
        PokerHand hand = new PokerHand();
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

        PokerHand.HandType result = hand.twoRankHandChooser();

        Assert.assertTrue(result == PokerHand.HandType.FULLHOUSE);
    }

}
