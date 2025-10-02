package domain;

import domain.model.Card;
import domain.model.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    @Test
    void testHandValueWithAce() {
        Hand hand = new Hand();
        hand.addCard(new Card("Hearts", "A", 11));
        hand.addCard(new Card("Clubs", "9", 9));
        assertEquals(20, hand.getValue());
    }
}
