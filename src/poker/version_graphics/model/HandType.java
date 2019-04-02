package poker.version_graphics.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

    public enum HandType {
        HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;


        /**
         * Determine the value of this hand. Note that this does not
         * account for any tie-breaking
         */


        public static HandType evaluateHand(ArrayList<Card> cards) {


            HandType currentEval = HighCard;

            if (isOnePair(cards)) currentEval = OnePair;
            if (isTwoPair(cards)) currentEval = TwoPair;
            if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
            if (isStraight(cards)) currentEval = Straight;
            if (isFlush(cards)) currentEval = Flush;
            if (isFullHouse(cards)) currentEval = FullHouse;
            if (isFourOfAKind(cards)) currentEval = FourOfAKind;
            if (isStraightFlush(cards)) currentEval = StraightFlush;
            if (isRoyalFlush(cards)) currentEval = RoyalFlush;
            return currentEval;
        }

        public static boolean isOnePair(ArrayList<Card> cards) {
            boolean found = false;
            for (int i = 0; i < cards.size() - 1 && !found; i++) {
                for (int j = i + 1; j < cards.size() && !found; j++) {
                    if (cards.get(i).getRank() == cards.get(j).getRank()){
                        found = true;
                        for(Card c:cards){
                            if (cards.get(i) == c || cards.get(j) == c)c.setCombo(true);
                        }
                    }
                }
            }
            return found;
        }

        public static boolean isTwoPair(ArrayList<Card> cards) {
            // Clone the cards, because we will be altering the list
            ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

            // Find the first pair; if found, remove the cards from the list
            boolean firstPairFound = false;
            for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
                for (int j = i + 1; j < clonedCards.size() && !firstPairFound; j++) {
                    if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                        firstPairFound = true;
                        for(Card c:cards){
                            if (clonedCards.get(i) == c || clonedCards.get(j) == c)c.setCombo(true);
                        }
                        clonedCards.remove(j);  // Remove the later card
                        clonedCards.remove(i);  // Before the earlier one
                    }
                }
            }
            // If a first pair was found, see if there is a second pair
            return firstPairFound && isOnePair(clonedCards);
        }

        public static boolean isThreeOfAKind(ArrayList<Card> cards) {
            boolean threeFound = false;
            for (int i = 0; i < cards.size() - 2 && !threeFound; i++) {
                for (int j = i + 1; j < cards.size() - 1 && !threeFound; j++) {
                    for (int k = j + 1; k < cards.size() && !threeFound; k++) {
                        if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(i).getRank() == cards.get(k).getRank()) {
                            threeFound = true;
                            for (Card c : cards) {
                                if (cards.get(i) == c || cards.get(j) == c || cards.get(k) == c) c.setCombo(true);
                            }
                        }
                    }
                }
            }
            return threeFound;
        }

        public static boolean isStraight(ArrayList<Card> cards) {
            boolean straightFound = false;
            int count = 0;
            ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
            clonedCards.sort(Comparator.comparing(Card::getRank));
            for (int i = 0; i < clonedCards.size() -1 ; i++) {
                if (clonedCards.get(i).getRank().ordinal() + 1 == clonedCards.get(i + 1).getRank().ordinal()) {
                    count++;
                    if (count == 4) {
                        straightFound = true;
                    }
                }
            }
            if (clonedCards.get(4).getRank() == Card.Rank.Ace && straightFound == false) {
                if (clonedCards.get(4).getRank() == Card.Rank.Ace && clonedCards.get(0).getRank() == Card.Rank.Two &&
                        clonedCards.get(1).getRank() == Card.Rank.Three && clonedCards.get(2).getRank() == Card.Rank.Four &&
                        clonedCards.get(3).getRank() == Card.Rank.Five) {
                    straightFound = true;
                }
            }

            return straightFound;
        }

        public static boolean isFlush(ArrayList<Card> cards) {
            boolean flushFound = false;
            for (int i = 0; i < cards.size() - 4 && !flushFound; i++) {
                for (int j = i + 1; j < cards.size() - 3 && !flushFound; j++) {
                    for (int k = j + 1; k < cards.size() - 2 && !flushFound; k++) {
                        for (int l = k + 1; l < cards.size() - 1 && !flushFound; l++) {
                            for (int m = l + 1; m < cards.size() && !flushFound; m++) {
                                if (cards.get(i).getSuit() == cards.get(j).getSuit() && cards.get(i).getSuit() == cards.get(k).getSuit()
                                        && cards.get(i).getSuit() == cards.get(l).getSuit() && cards.get(i).getSuit() == cards.get(m).getSuit()) {
                                    flushFound = true;
                                }
                            }
                        }
                    }
                }
            }
            return flushFound;
        }

        public static boolean isFullHouse(ArrayList<Card> cards) {
            ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
            boolean firstThreeFound = false;
            boolean pairFound = false;
            for (int i = 0; i < clonedCards.size() - 2 && !firstThreeFound; i++) {
                for (int j = i + 1; j < clonedCards.size() - 1 && !firstThreeFound; j++) {
                    for (int k = j + 1; k < clonedCards.size() && !firstThreeFound; k++) {
                        if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(i).getRank() == clonedCards.get(k).getRank()) {
                            firstThreeFound = true;
                            for (Card c : cards) {
                                if (clonedCards.get(i) == c || clonedCards.get(j) == c || clonedCards.get(k) == c)
                                    c.setCombo(true);
                            }
                            clonedCards.remove(k);  // Remove the later card
                            clonedCards.remove(j);  // Before the earlier one
                            clonedCards.remove(i);  // Even earlier
                        }
                    }
                }
            }

            return firstThreeFound &&  isOnePair(clonedCards);
        }

        public static boolean isFourOfAKind(ArrayList<Card> cards) {
            boolean fourFound = false;
            for (int i = 0; i < cards.size() - 3 && !fourFound; i++) {
                for (int j = i + 1; j < cards.size() - 2 && !fourFound; j++) {
                    for (int k = j + 1; k < cards.size() - 1 && !fourFound; k++) {
                        for (int l = k + 1; l < cards.size() && !fourFound; l++)
                            if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(i).getRank() == cards.get(k).getRank() && cards.get(i).getRank() == cards.get(l).getRank()) {
                                fourFound = true;
                                for(Card c:cards){
                                    if (cards.get(i) == c || cards.get(j) == c || cards.get(k) == c || cards.get(l)== c )c.setCombo(true);
                                }
                            }
                    }
                }
            }
            return fourFound;


        }

        public static boolean isStraightFlush(ArrayList<Card> cards) {
            boolean straightFlushFound = false;
            if (isStraight(cards) && isFlush(cards) == true) straightFlushFound = true;
            return straightFlushFound;
        }

        public static boolean isRoyalFlush(ArrayList<Card> cards) {
            ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
            clonedCards.sort(Comparator.comparing(Card::getRank));
            boolean royalFlushFound = false;
            if (isFlush(cards) == true &&
            clonedCards.get(0).getRank() == Card.Rank.Ten &&
            clonedCards.get(1).getRank() == Card.Rank.Jack &&
            clonedCards.get(2).getRank() == Card.Rank.Queen &&
            clonedCards.get(3).getRank() == Card.Rank.King &&
            clonedCards.get(4).getRank() == Card.Rank.Ace)
            royalFlushFound = true;
            return royalFlushFound;
        }

        public static String isHighest(ArrayList<String> tocheck){
            String highest = "";


            return highest;
        }


    }


