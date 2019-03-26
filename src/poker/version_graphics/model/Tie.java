package poker.version_graphics.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tie {

public static int findWinner(Player player1, Player player2) {

int result = 99;
        ArrayList<Card> hand1Combo = new ArrayList<Card>();
        ArrayList<Card> hand1High = new ArrayList<>();
        ArrayList<Card> hand2Combo = new ArrayList<Card>();
        ArrayList<Card> hand2High = new ArrayList<>();

switch (player1.getHandType()) {


            case FullHouse: {
                hand1Combo = fullHouseSort(player1.getCards()); //Sort FullHouse so that triples are always in the back of the list
                hand2Combo = fullHouseSort(player2.getCards());
                break;
            }

            case Straight: {
                for (Card c : player1.getCards())
                {
                    hand1Combo.add(c);
                }
                hand1Combo.sort(Comparator.comparing(Card::getRank));
                if(hand1Combo.get(0).getRank() == Card.Rank.Two && //Check if low Straight
                hand1Combo.get(1).getRank() == Card.Rank.Three &&
                hand1Combo.get(2).getRank() == Card.Rank.Four &&
                hand1Combo.get(3).getRank() == Card.Rank.Five &&
                hand1Combo.get(4).getRank() == Card.Rank.Ace){
                    Collections.rotate(hand1Combo,1); //Put Ace at first position
                }

                for (Card c : player2.getCards()) //add cards from player2
                {
                    hand2Combo.add(c);
                }
                hand2Combo.sort(Comparator.comparing(Card::getRank));
                if(hand2Combo.get(0).getRank() == Card.Rank.Two && //check if low Straight
                        hand2Combo.get(1).getRank() == Card.Rank.Three &&
                        hand2Combo.get(2).getRank() == Card.Rank.Four &&
                        hand2Combo.get(3).getRank() == Card.Rank.Five &&
                        hand2Combo.get(4).getRank() == Card.Rank.Ace){
                    Collections.rotate(hand2Combo,1); //Put Ace at first position
                }
                break;

            }
            default: {

                for (Card c : player1.getCards())
                {
                    if (c.getCombo() == true ) hand1Combo.add(c); //add cards from players
                    if (c.getCombo() == false) hand1High.add(c);
                }
                for (Card c : player2.getCards())
                {
                    if (c.getCombo() == true ) hand2Combo.add(c);
                    if (c.getCombo() == false) hand2High.add(c);
                    //&& !hand2Combo.contains(c.getRank()) not working
                }
                hand1Combo.sort(Comparator.comparing(Card::getRank));
                hand1High.sort(Comparator.comparing(Card::getRank));
                hand2Combo.sort(Comparator.comparing(Card::getRank));
                hand2High.sort(Comparator.comparing(Card::getRank));
                break;
            }
}


            if (hand1High.size() == 5){ //Check the hands that dont have a combination against each other starting from the end
                int highCounter = hand1High.size() - 1;
                while (result == 99) {
                    if (hand1High.get(highCounter).getRank().ordinal() > hand2High.get(highCounter).getRank().ordinal()) {
                        result = 1;
                    }
                    if (hand1High.get(highCounter).getRank().ordinal() < hand2High.get(highCounter).getRank().ordinal()) {
                        result = -1;
                    }
                    if (hand1High.get(highCounter).getRank().ordinal() == hand2High.get(highCounter).getRank().ordinal()) {

                        if (highCounter == 0) {
                            result = 100;
                        }
                    }
                    highCounter--;

                }
            }
            else {
                int comboCounter = hand1Combo.size() - 1;
                int highCounter;
               if(hand1High.size() > 0 && hand2High.size() > 0) {
                   highCounter = hand1High.size() - 1;
               }
               else {
                    highCounter = 0; //Check the hands that have a combination against each other starting from the end
               }
                while (result == 99) {
                    if (hand1Combo.get(comboCounter).getRank().ordinal() > hand2Combo.get(comboCounter).getRank().ordinal()) {
                        result = 1;
                    }
                    if (hand1Combo.get(comboCounter).getRank().ordinal() < hand2Combo.get(comboCounter).getRank().ordinal()) {
                        result = -1;
                    }
                    if (hand1Combo.get(comboCounter).getRank().ordinal() == hand2Combo.get(comboCounter).getRank().ordinal()) {

                        if (comboCounter == 0) {

                                if (hand1High.get(highCounter).getRank().ordinal() > hand2High.get(highCounter).getRank().ordinal()) {
                                    result = 1;
                                    break;
                                }
                                if (hand1High.get(highCounter).getRank().ordinal() < hand2High.get(highCounter).getRank().ordinal()) {
                                    result = -1;
                                    break;
                                }
                                if (hand1High.get(highCounter).getRank().ordinal() == hand2High.get(highCounter).getRank().ordinal()) {
                                    if (highCounter == 0) {
                                        result = 100;
                                    }
                                }
                        if (highCounter == 0) {
                            result = 100;
                            }
                            highCounter--;
                        }
                    }
                    comboCounter--;
                }

            }
        return result;
        }



                   public static ArrayList<Card> fullHouseSort (ArrayList < Card > cards) {
                       ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
                       ArrayList<Card> sortedCards = new ArrayList<Card>();
                       clonedCards.sort(Comparator.comparing(Card::getRank));
                       boolean firstThreeFound = false;
                       boolean pairFound = false;
                       for (int i = 0; i < clonedCards.size() - 2 && !firstThreeFound; i++) {
                           for (int j = i + 1; j < clonedCards.size() - 1 && !firstThreeFound; j++) {
                               for (int k = j + 1; k < clonedCards.size() && !firstThreeFound; k++) {
                                   if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(i).getRank() == clonedCards.get(k).getRank()) {
                                    sortedCards.add(clonedCards.get(i));
                                    sortedCards.add(clonedCards.get(j));
                                    sortedCards.add(clonedCards.get(k));
                                    clonedCards.remove(k);
                                    clonedCards.remove(j);
                                    clonedCards.remove(i);
                                    sortedCards.add(0,clonedCards.get(0));
                                    sortedCards.add(0, clonedCards.get(1));
                                   }
                               }
                           }
                       }
                       return sortedCards;
                   }


}


  /*  public static ArrayList<Card> tieHighPair(ArrayList<Card> cards) {
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        ArrayList<Card> highCard = new ArrayList<Card>();
        boolean found = false;
        for (int i = 0; i < clonedCards.size() - 1 && !found; i++) {
            for (int j = i + 1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) {
                 highCard.add(cards.get(i));
                 found = true;
                // highCard.add(cards.get(j));
                }
            }

        }

      return highCard;
    }

    public static ArrayList tieTwoPair(ArrayList<Card> cards) {
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        ArrayList<Card> highCard = new ArrayList<Card>();
        boolean found = false;
        boolean found2 = false;
        for (int i = 0; i < clonedCards.size() - 1 && !found ; i++) {
            for (int j = i + 1; j < clonedCards.size() && !found; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    highCard.add(clonedCards.get(i));
                    found = true;
                    clonedCards.remove(j);
                    clonedCards.remove(i);
                }
            }
        }// Before the earlier one
        for (int k = 0; k < clonedCards.size() - 1 && !found2 ; k++) {
            for (int l = k + 1; l < clonedCards.size() && !found2 ; l++) {
                if (clonedCards.get(k).getRank() == clonedCards.get(l).getRank()) {
                    highCard.add(clonedCards.get(k));
                    found2 = true;
                }
            }
        }
        return highCard;
    }

    public static ArrayList tieThreeOfAKind(ArrayList<Card> cards) {
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        ArrayList<Card> highCard = new ArrayList<Card>();
        boolean found = false;
        for (int i = 0; i < clonedCards.size() - 2 && !found ; i++) {
            for (int j = i + 1; j < clonedCards.size() - 1 && !found ; j++) {
                for (int k = j + 1; k < clonedCards.size(); k++) {
                    if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(i).getRank() == clonedCards.get(k).getRank()){
                        highCard.add(clonedCards.get(i));
                        found = true;
                    }
                }
            }
        }
        return highCard;
    }

    public static ArrayList tieFourOfAKind(ArrayList<Card> cards) {
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        ArrayList<Card> highCard = new ArrayList<Card>();
        boolean found = false;
        for (int i = 0; i < clonedCards.size() - 3 && !found ; i++) {
            for (int j = i + 1; j < clonedCards.size() - 2 && !found; j++) {
                for (int k = j + 1; k < clonedCards.size() - 1 && !found; k++) {
                    for (int l = k + 1; l < clonedCards.size() && !found; l++)
                        if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(i).getRank() == clonedCards.get(k).getRank() && clonedCards.get(i).getRank() == clonedCards.get(l).getRank()) {
                            highCard.add(clonedCards.get(i));
                            found = true;
                        }
                }
            }
        }
        return highCard;
    }

        public static ArrayList tieFullHouse (ArrayList < Card > cards) {
            // TODO
            ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
            ArrayList<Card> highCard = new ArrayList<Card>();
            boolean found = false;
            boolean found2 = false;
            for (int i = 0; i < clonedCards.size() - 2 && !found; i++) {
                for (int j = i + 1; j < clonedCards.size() - 1 && !found; j++) {
                    for (int k = j + 1; k < clonedCards.size() && !found; k++) {
                        if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(i).getRank() == clonedCards.get(k).getRank())
                            highCard.add(clonedCards.get(i));
                        clonedCards.remove(k);  // Remove the later card
                        clonedCards.remove(j);  // Before the earlier one
                        clonedCards.remove(i);  // Even earlier
                    }
                }
            }
            for (int l = 0; l < clonedCards.size() - 1 && !found2 ; l++) {
                for (int m = l + 1; m < clonedCards.size() && !found2 ; m++) {
                    if (clonedCards.get(l).getRank() == clonedCards.get(m).getRank()) {
                        highCard.add(clonedCards.get(l));
                        found2 = true;
                    }
                }
            }
            return highCard;
        }
*/

