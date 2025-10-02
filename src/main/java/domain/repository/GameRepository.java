package domain.repository;

import domain.model.Player;

import java.util.Optional;

public interface GameRepository {
    void save(Player player);
    Optional<Player> findByName(String name);
    void delete(String name);
}
