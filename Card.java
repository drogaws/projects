package uno;

enum Color {
    RED, YELLOW, GREEN, BLUE, WILD
};

enum Type {
    NUMBER, REVERSE, DRAW_TWO, SKIP, WILD, WILD_DRAW_FOUR
};

public class Card {

    Color color;
    Type type;
    int number;

    public Card(Color color, Type type, int number) {
        this.color = color;
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        String str;
        if (type == Type.NUMBER) {
            str = this.color + " (" + this.number + ")";
        } else if (color == Color.WILD) {
            str = this.type + "!";
        } else {
            str = this.color + " " + this.type;
        }
        return str;
    }
}
