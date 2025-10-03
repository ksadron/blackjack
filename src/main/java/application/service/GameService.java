package application.service;

import application.usecase.*;
import domain.model.Player;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GameService implements GameCreateUseCase, GameDeleteUseCase, GameReadUseCase, GameUpdateUseCase {

    private final Map<String, Player> players = new ConcurrentHashMap<>();

    @Override
    public void createPlayer(String name) {
        if (!players.containsKey(name)) {
            players.put(name, new Player(name));
            System.out.println("Player created: " + name);
        } else {
            System.out.println("Player already exists: " + name);
        }
    }

    @Override
    public void removePlayer(String name) {
        if (players.remove(name) != null) {
            System.out.println("Player removed: " + name);
        } else {
            System.out.println("Player not found: " + name);
        }
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        return Optional.ofNullable(players.get(name));
    }

    @Override
    public void hit(String playerName) {
        Player player = players.get(playerName);
        if (player != null) {

            System.out.println(playerName + " hits");
        } else {
            System.out.println("Player not found: " + playerName);
        }
    }

    @Override
    public void stand(String playerName) {
        Player player = players.get(playerName);
        if (player != null) {

            System.out.println(playerName + " stands");
        } else {
            System.out.println("Player not found: " + playerName);
        }
    }
}
