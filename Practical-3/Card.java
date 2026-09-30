import java.util.Objects;

public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card c = (Card) obj;
        return Objects.equals(rank, c.rank) && Objects.equals(suit, c.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    public static void main(String[] args) {
        Card[] cards = new Card[5];
        Card[] toAdd = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Queen", "Clubs"),
            new Card("Ace", "Spades")
        };

        int count = 0;
        for (Card card : toAdd) {
            boolean duplicate = false;
            for (int j = 0; j < count; j++) {
                if (card.equals(cards[j])) {
                    System.out.println("Duplicate found: " + card);
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate && count < cards.length) {
                cards[count++] = card;
            }
        }
    }
}
