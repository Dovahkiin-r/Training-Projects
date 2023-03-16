import java.util.Scanner;
class Player {
    private String name;
    private MapObject sign;

    Player (String name, MapObject sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName () {
        return this.name;
    }
    public MapObject getSign () {
        return this.sign;
    }

    public int [] getInput () throws IllegalArgumentException {
        Scanner sc = new Scanner (System.in);
        int [] input = new int[2];
        System.out.println ("get the coordinates the cell, that you want to play ");
        try {
            input [0] = sc.nextInt() - 1;
            input [1] = sc.nextInt() - 1;
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException ("You should enter numbers!");
        }
        if (!(input[0] >= 0 && input[0] <= 2 && input[1] >= 0 && input[1] <= 2 )) {
            throw new IllegalArgumentException ("Coordinates should be from 1 to 3!");
        }
        return input;
    }
}
