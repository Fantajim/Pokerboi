package poker.version_graphics.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TieTest {

    private static String[] p1HighCard =
            { "AC", "KC", "TC", "5C", "7C" }; //winner
    private static String[] p2HighCard =
            { "AD", "KD", "9D", "5D", "7D" }; //looser
    private static String[] p1DoublePair =
            { "AC", "AD", "KC", "KD", "5D" }; //looser
    private static String[] p2DoublePair =
            { "AH", "AS", "KH", "KS", "6H" }; //winner
    private static String[] p1FullHouse =
            { "AC", "AD", "AH", "5C", "5D" }; //winner
    private static String[] p2FullHouse =
            { "KC", "KD", "KH", "8C", "8D" }; //looser
    private static String[] p1Fours =
            { "AC", "AD", "AH", "5C", "AS" }; //winner
    private static String[] p2Fours =
            { "KC", "KD", "KH", "KS", "8D" }; //looser
    private static String[] p1Straight =
            { "TC", "JD", "QH", "KC", "AS" }; //winner
    private static String[] p2Straight =
            { "AC", "2S", "3H", "4C", "5D" }; //looser
    private static String[] p1StraightLow =
            { "3C", "4D", "5H", "6C", "7S" }; //winner
    private static String[] p2StraightLow =
            { "AC", "2S", "3H", "4C", "5D" }; //looser

    ArrayList<Card> p1highCardHands;
    ArrayList<Card> p2highCardHands;
    ArrayList<Card> p1DoublePairHands;
    ArrayList<Card> p2DoublePairHands;
    ArrayList<Card> p1FullHouseHands;
    ArrayList<Card> p2FullHouseHands;
    ArrayList<Card> p1FourHands;
    ArrayList<Card> p2FourHands;
    ArrayList<Card> p1StraightHands;
    ArrayList<Card> p2StraightHands;
    ArrayList<Card> p1StraightHandsLow;
    ArrayList<Card> p2StraightHandsLow;
    Player p1 = new Player("Testplayer1");
    Player p2 = new Player("Testplayer2");

    @Before
    public void makeHands() {
        p1highCardHands = makeHand(p1HighCard);
        p2highCardHands = makeHand(p2HighCard);
        p1DoublePairHands = makeHand(p1DoublePair);
        p2DoublePairHands = makeHand(p2DoublePair);
        p1FullHouseHands = makeHand(p1FullHouse);
        p2FullHouseHands = makeHand(p2FullHouse);
        p1FourHands = makeHand(p1Fours);
        p2FourHands = makeHand (p2Fours);
        p1StraightHands = makeHand(p1Straight);
        p2StraightHands = makeHand(p2Straight);
        p1StraightHandsLow = makeHand(p1StraightLow);
        p2StraightHandsLow = makeHand(p2StraightLow);
    }

    @Test
    public void testHighCard() {
        for(Card c:p1highCardHands){
            p1.addCard(c);
        }
        for (Card c :p2highCardHands){
            p2.addCard(c);
        }
        p1.evaluateHand();
        p2.evaluateHand();
        int test1 = Tie.findWinner(p1,p2);
      assertEquals(test1,1); //p1 winner
    }

    @Test
    public void testDoublePair() {
        for(Card c:p1DoublePairHands){
            p1.addCard(c);
        }
        for (Card c :p2DoublePairHands){
            p2.addCard(c);
        }
        p1.evaluateHand();
        p2.evaluateHand();
       int test1 = Tie.findWinner(p1,p2);
        assertEquals(test1,-1); //p2 winner
    }

    @Test
    public void testFullHouse() {
        for(Card c:p1FullHouseHands){
            p1.addCard(c);
        }
        for (Card c :p2FullHouseHands){
            p2.addCard(c);
        }
        p1.evaluateHand();
        p2.evaluateHand();
        int test1 = Tie.findWinner(p1,p2);
        assertEquals(test1,1); //p1 winner
    }

    @Test
    public void testFours() {
        for(Card c:p1FourHands){
            p1.addCard(c);
        }
        for (Card c :p2FourHands){
            p2.addCard(c);
        }
        p1.evaluateHand();
        p2.evaluateHand();
        int test1 = Tie.findWinner(p1,p2);
        assertEquals(test1,1); //p1 winner
    }

    @Test
    public void testStraight() {
        for(Card c:p1StraightHands){
            p1.addCard(c);
        }
        for (Card c :p2StraightHands){
            p2.addCard(c);
        }
        p1.evaluateHand();
        p2.evaluateHand();
        int test1 = Tie.findWinner(p1,p2);
        assertEquals(test1,1); //p1 winner
    }


    @Test
    public void testStraightLow() {
        for(Card c:p1StraightHandsLow){
            p1.addCard(c);
        }
        for (Card c :p2StraightHandsLow){
            p2.addCard(c);
        }
        p1.evaluateHand();
        p2.evaluateHand();
        int test1 = Tie.findWinner(p1,p2);
        assertEquals(test1,1); //p1 winner
    }

    /**
     * Make a hand (ArrayList<Card>) from an array of 5 strings
     */
    private ArrayList<Card> makeHand(String[] inStrings) {
        ArrayList<Card> hand = new ArrayList<>();
        for (String in : inStrings) {
            hand.add(makeCard(in));
        }
        return hand;
    }

    /**
     * Create a card from a 2-character String.
     * First character is the rank (2-9, T, J, Q, K, A)
     * Second character is the suit (C, D, H, S)
     *
     * No validation or error handling!
     */
    private Card makeCard(String in) {
        char r = in.charAt(0);
        Card.Rank rank = null;
        if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
        else if (r == 'T') rank = Card.Rank.Ten;
        else if (r == 'J') rank = Card.Rank.Jack;
        else if (r == 'Q') rank = Card.Rank.Queen;
        else if (r == 'K') rank = Card.Rank.King;
        else if (r == 'A') rank = Card.Rank.Ace;

        char s = in.charAt(1);
        Card.Suit suit = null;
        if (s == 'C') suit = Card.Suit.Clubs;
        if (s == 'D') suit = Card.Suit.Diamonds;
        if (s == 'H') suit = Card.Suit.Hearts;
        if (s == 'S') suit = Card.Suit.Spades;

        return new Card(suit, rank);
    }
}
