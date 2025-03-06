package uno;

import java.util.ArrayList;

public class Player {

    ArrayList<Card> hand;
    String name;

    public Player(String name) {
        this.hand = new ArrayList<>();
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public void draw(Deck deck) {
        hand.add(deck.drawCard());
    }

    public void play(int i) {
        hand.remove(i);
    }

    public boolean checkHand() {
        return this.hand.isEmpty();
    }

    public String displayHand(int index) {
        return hand.get(index).toString();
    }

}
