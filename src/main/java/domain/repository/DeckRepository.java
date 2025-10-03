package domain.repository;

import domain.model.Deck;
import domain.model.Card;

import java.util.Optional;

public interface DeckRepository {
    void save(Deck deck);
    Optional<Deck> findById(String id);


    void delete(String id);
    Optional<Card> drawCard(String id);
}
