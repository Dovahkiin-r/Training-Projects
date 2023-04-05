package tictactoe;
import static tictactoe.Functions.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //get map from user (use for test)
        /*
        Scanner sc = new Scanner(System.in);
        String loadMap = sc.nextLine();
        */

        while (!exit) {
            //build the map and players
            Map map = new Map();
            Player[] players = new Player[2];
            Player.isFirstPlayer = true;
            try {
                players = getInputStart();
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                continue;
            }
            if (exit) {
                break;
            }
            map.printMap();
            // play loop
            int turn = 0;
            int[] coords = new int[2];
            boolean gameEnd = false;
            String winer = "";
            while (!gameEnd && turn < 9) {
                try {
                    coords = players[turn % 2].coordsToplay(map);
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                    continue;
                }
                if (exit) {
                    break;
                }
                map.addAtCoord(coords[0], coords[1], players[turn % 2].getSign());
                map.printMap();
                //check game state
                gameEnd = map.isGameOver();
                if(gameEnd) {
                    winer += players[turn % 2].getSign() + " wins";
                    break;
                }
                ++turn;
            }
            //print result
            if (gameEnd) {
                System.out.println(winer);
            } else {
                System.out.println("Draw");
            }
        }
    }
}