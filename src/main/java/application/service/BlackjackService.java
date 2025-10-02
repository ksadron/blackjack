package application.service;

import application.usecase.*;
import domain.exceptions.BlackjackException;
import domain.model.*;
import domain.repository.GameRepository;

public class BlackjackService implements GameCreateUseCase, GameReadUseCase, GameUpdateUseCase, GameDeleteUseCase {
    private final GameRepository repository;
    private final Deck deck;

    public BlackjackService(GameRepository repository) {
        this.repository = repository;
        this.deck = new Deck();
    }

    @Override
    public void createPlayer(String name) {
        Player player = new Player(name);
        player.getHand().addCard(deck.draw());
        player.getHand().addCard(deck.draw());
        repository.save(player);
    }

    @Override
    public java.util.Optional<Player> getPlayer(String name) {
        return repository.findByName(name);
    }

    @Override
    public void hit(String playerName) {
        Player player = repository.findByName(playerName)
                .orElseThrow(() -> new BlackjackException("Player not found"));
        player.getHand().addCard(deck.draw());
    }

    @Override
    public void stand(String playerName) {
        // Można tu dodać logikę rozgrywki z krupierem
    }

    @Override
    public void removePlayer(String name) {
        repository.delete(name);
    }
}
