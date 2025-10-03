package domain.repository;

import domain.model.Hand;

import java.util.Optional;

public interface HandRepository {
    void save(String playerId, Hand hand);
    Optional<Hand> findByPlayerId(String playerId);
    void delete(String playerId);
}
