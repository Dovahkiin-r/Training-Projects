package tictactoe;

public abstract class Player {
    private String name;
    private char sign;
    static boolean isFirstPlayer = true;

    Player() {
        if (isFirstPlayer) {
            this.sign = 'X';
            this.name = "Player 1";
            isFirstPlayer = false;
        } else {
            this.sign = 'O';
            this.name = "Player 2";
        }
    }
    Player(String name) {
        if (isFirstPlayer) {
            this.sign = 'X';
            this.name = "Player 1";
            isFirstPlayer = false;
        } else {
            this.sign = 'O';
        }
        this.name = name;
    }

    public char getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }

    abstract int[] coordsToplay (Map map);
}
