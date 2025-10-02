package app;

import application.service.BlackjackService;
import domain.model.Deck;
import domain.model.Player;
import domain.repository.GameRepository;

import java.util.Scanner;
import java.util.stream.Collectors;

class InMemoryGameRepository implements GameRepository {
    private final java.util.Map<String, Player> players = new java.util.HashMap<>();

    @Override
    public void save(Player player) {
        players.put(player.getName(), player);
    }

    @Override
    public java.util.Optional<Player> findByName(String name) {
        return java.util.Optional.ofNullable(players.get(name));
    }

    @Override
    public void delete(String name) {
        players.remove(name);
    }
}

public class Main {
    public static void main(String[] args) {
        GameRepository repo = new InMemoryGameRepository();
        BlackjackService service = new BlackjackService(repo);
        Scanner sc = new Scanner(System.in);

        Deck deck = new Deck();

        System.out.println("=== BLACKJACK ===");
        System.out.print("Podaj imię gracza: ");
        String name = sc.nextLine();

        // --- GRACZ ---
        service.createPlayer(name);
        Player player = service.getPlayer(name).orElseThrow();

        // --- KRUPIER ---
        Player dealer = new Player("Dealer");
        dealer.getHand().addCard(deck.draw()); // odkryta
        dealer.getHand().addCard(deck.draw()); // zakryta

        boolean playing = true;
        while (playing) {
            // Karty gracza
            System.out.println("\nTwoje karty: " +
                    player.getHand().getCards().stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(", ")));
            System.out.println("Suma: " + player.getHand().getValue());

            // Pokazanie tylko jednej karty krupiera
            System.out.println("Karta krupiera: " + dealer.getHand().getCards().get(0) + " i [zakryta]");

            // Sprawdzenie bust gracza
            if (player.getHand().getValue() > 21) {
                System.out.println("BUST! Przegrałeś.");
                break;
            }

            System.out.print("Co robisz? (hit/stand): ");
            String action = sc.nextLine();

            if (action.equalsIgnoreCase("hit")) {
                service.hit(name);
            } else if (action.equalsIgnoreCase("stand")) {
                // --- Odsłonięcie zakrytej karty ---
                System.out.println("\nKarty krupiera: " +
                        dealer.getHand().getCards().stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", ")));
                System.out.println("Suma krupiera (na start): " + dealer.getHand().getValue());

                // --- Dobieranie przez krupiera ---
                while (dealer.getHand().getValue() < 17) {
                    dealer.getHand().addCard(deck.draw());
                }

                System.out.println("Ostateczne karty krupiera: " +
                        dealer.getHand().getCards().stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", ")));
                System.out.println("Suma krupiera: " + dealer.getHand().getValue());

                // --- Wynik gry ---
                int playerValue = player.getHand().getValue();
                int dealerValue = dealer.getHand().getValue();

                if (dealerValue > 21 || playerValue > dealerValue) {
                    System.out.println("WYGRAŁEŚ!");
                } else if (dealerValue == playerValue) {
                    System.out.println("REMIS.");
                } else {
                    System.out.println("PRZEGRAŁEŚ.");
                }
                playing = false;
            } else {
                System.out.println("Nieznana komenda.");
            }
        }

        System.out.println("\nKoniec gry!");
    }
}
