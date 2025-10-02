package application;

import application.service.BlackjackService;
import domain.model.Player;
import org.junit.jupiter.api.Test;
import test.InMemoryGameRepository;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackServiceTest {
    @Test
    void testCreatePlayer() {
        InMemoryGameRepository repo = new InMemoryGameRepository();
        BlackjackService service = new BlackjackService(repo);

        service.createPlayer("Jan");
        Player player = service.getPlayer("Jan").orElseThrow();
        assertNotNull(player.getHand());
        assertTrue(player.getHand().getCards().size() >= 2);
    }
}
