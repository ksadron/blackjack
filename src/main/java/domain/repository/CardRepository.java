package domain.repository;

import domain.model.Card;

import java.util.Optional;

public interface CardRepository {
    void save(Card card);
    Optional<Card> findByRankAndSuit(String rank, String suit);
    void delete(String rank, String suit);
}
