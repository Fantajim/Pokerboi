package poker.version_text.model;

public class Card {
    public enum Suit { Clubs, Diamonds, Hearts, Spades;
        @Override
        public String toString() {
            char symbol = 0x0000;
            switch (this) {
            case Clubs: symbol = 0x2663; break;
            case Diamonds: symbol = 0x2666; break;
            case Hearts: symbol = 0x2665; break;
            case Spades: symbol = 0x2660; break;
            }
            return String.valueOf(symbol);
        }
    };
    
    public enum Rank { Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
        @Override
        public String toString() {
            String str = "A";  // Assume we have an ace, then cover all other cases
            // Get ordinal value, which ranges from 0 to 12
            int ordinal = this.ordinal();
            if (ordinal <= 8) {
                str = Integer.toString(ordinal+2);
            } else if (ordinal == 9) { // Jack
                str = "J";
            } else if (ordinal == 10) { // Queen
                str = "Q";
            } else if (ordinal == 11) { // King
                str = "K";
            }
            return str;
        }
    };
    
    private final Suit suit;
    private final Rank rank;
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
