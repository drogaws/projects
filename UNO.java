package uno;

import java.util.ArrayList;

public class UNO {

    public static void main(String[] args) {
        String[] playerNames = {"Jadon", "Aby"};
        Game game = new Game(playerNames);
        game.play();
    }
}
