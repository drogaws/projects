package uno;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    //DATA
    ArrayList<Player> players = new ArrayList<>();
    Deck deck;
    Card topCard;
    Player currentPlayer;
    int direction = 0;

    //Constructor
    public Game(String[] names) {
        //Declare Deck & Players
        this.deck = new Deck();
        createPlayers(names);
        this.topCard = this.deck.drawCard();
        this.currentPlayer = players.getFirst();
    }

    //CODE
    void play() {
        //Delcare whats needed for game logic
        Scanner scanner = new Scanner(System.in);
        int choice;

        //GAME LOOP
        while (true) {
            while (true) {
                System.out.println("*Top Card: " + topCard + "*");
                playerHand();
                //Grab Input
                try {
                    choice = playerTurn(scanner);
                    if (!validInput(choice)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input...");
                    continue;
                }

                //Draw
                if (choice == -1) {
                    currentPlayer.draw(deck);
                    if (validChoice(currentPlayer.hand.size() - 1)) {
                        continue;
                    }
                    //Non Number Card
                } else if (validChoice(choice) && currentPlayer.hand.get(choice).type != Type.NUMBER) {

                    topCard = currentPlayer.hand.get(choice);
                    currentPlayer.play(choice);
                    wildCardLogic(topCard, scanner);

                    //Numbered Card
                } else if (validChoice(choice)) {
                    topCard = currentPlayer.hand.get(choice);
                    currentPlayer.play(choice);

                    //Invalid input
                } else {
                    System.out.println("Invalid Input...");
                    continue;
                }
                break;
            }

            if (currentPlayer.checkHand()) {
                System.out.println(currentPlayer.name + " WINS!");
                scanner.close();
                break;
            } else {
                nextTurn();
            }
        }

    }

  
    //Functions
    private boolean validInput(int choice) {
        return choice >= -1 && choice <= (currentPlayer.hand.size() - 1);
    }

    private boolean validChoice(int choice) {
        return currentPlayer.hand.get(choice).color == topCard.color ||
                currentPlayer.hand.get(choice).number == topCard.number ||
                currentPlayer.hand.get(choice).color == Color.WILD;
    }

    private void wildCardLogic(Card cardPlayed, Scanner scanner) {
        if (cardPlayed.type == Type.WILD) {
            wildCardLogic(scanner);
        } else if (cardPlayed.type == Type.WILD_DRAW_FOUR) {
            wildCardLogic(scanner);
            drawFourLogic();
        } else if (cardPlayed.type == Type.SKIP) {
            nextTurn();
        } else if (cardPlayed.type == Type.DRAW_TWO) {
            drawTwoLogic();
        } else if (cardPlayed.type == Type.REVERSE) {
            reverseLogic();
        }
    }

    private void playerHand() {
        System.out.println(currentPlayer.name + "'s Cards.");
        for (int i = 0; i < currentPlayer.hand.size(); i++) {
            System.out.println(i + ": " + currentPlayer.displayHand(i));
        }
    }

    private void createPlayers(String[] names) {
        for (String name : names) {
            players.add(new Player(name));
        }
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.draw(deck);
            }
        }
    }

    private int playerTurn(Scanner scanner) {
        System.out.println("Select a card 0-" + (currentPlayer.hand.size() - 1) + " (Draw -1): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

    private void nextTurn() {
        int currentIndex = players.indexOf(currentPlayer);
        if (direction % 2 == 0) {
            int nextIndex = (currentIndex + 1) % players.size();
            currentPlayer = players.get(nextIndex);
        } else {
            int nextIndex = (currentIndex - 1 + players.size()) % players.size();
            currentPlayer = players.get(nextIndex);
        }

    }

    private void reverseLogic() {
        if (players.size() == 2) {
            nextTurn();
        } else {
            direction += 1;
        }
    }

    private void wildCardLogic(Scanner scanner) {
        while (true) {
            System.out.println("Choose a color (R)ed,(Y)ellow,(G)reen,(B)lue: ");
            String choice = scanner.nextLine();
            char letter = Character.toLowerCase(choice.charAt(0));

            switch (letter) {
                case ('r') -> {
                    topCard.color = Color.RED;
                    return;
                }
                case ('y') -> {
                    topCard.color = Color.YELLOW;
                    return;
                }
                case ('g') -> {
                    topCard.color = Color.GREEN;
                    return;
                }
                case ('b') -> {
                    topCard.color = Color.BLUE;
                    return;
                }
                default -> {
                    System.out.println("Invalid nigga");
                }
            }
        }
    }

    private void drawTwoLogic() {
        nextTurn();
        currentPlayer.draw(deck);
        currentPlayer.draw(deck);
    }

    private void drawFourLogic() {
        nextTurn();
        currentPlayer.draw(deck);
        currentPlayer.draw(deck);
        currentPlayer.draw(deck);
        currentPlayer.draw(deck);
    }
}
