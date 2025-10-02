package domain.model;

public class Player {
    private final String name;
    private final Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() { return name; }
    public Hand getHand() { return hand; }
}
