package domain.model;

import java.util.*;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int total = 0;
        int aces = 0;
        for (Card c : cards) {
            total += c.getValue();
            if (c.getRank().equals("A")) aces++;
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
