package uno;

import java.util.ArrayList;
import java.util.Collections;

//Figue out why it wanted it Final ; lines 8,14...
public final class Deck {

    ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        createDeck();
        shuffleDeck();
    }

    public void createDeck() {
        Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
        for (Color color : colors) {
            //Number Cards
            for (int i = 0; i <= 9; i++) {
                cards.add(new Card(color, Type.NUMBER, i));
            }
            //Skip,Reverse,Draw_Two
            for (int i = 0; i < 2; i++) {
                cards.add(new Card(color, Type.DRAW_TWO, 10));
                cards.add(new Card(color, Type.SKIP, 11));
                cards.add(new Card(color, Type.REVERSE, 12));
            }
        }
      
        //Wild,Wild_Draw_Four
        for (int i = 0; i < 2; i++) {
            cards.add(new Card(Color.WILD, Type.WILD, 13));
            cards.add(new Card(Color.WILD, Type.WILD_DRAW_FOUR, 14));
        }
    }

    //Functions
    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return this.cards.remove(0);
    }
}
