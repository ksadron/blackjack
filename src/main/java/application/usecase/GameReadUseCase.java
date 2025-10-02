package application.usecase;

import domain.model.Player;
import java.util.Optional;

public interface GameReadUseCase {
    Optional<Player> getPlayer(String name);
}
