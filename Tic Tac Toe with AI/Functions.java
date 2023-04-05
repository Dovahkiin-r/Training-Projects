package tictactoe;

import java.util.Scanner;

public class Functions {
    static boolean exit = false;
    static int[][] winCond = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public static Player[] getInputStart() {
        String[] userInput;
        Scanner sc = new Scanner(System.in);
        System.out.print("Input command: ");
        userInput = sc.nextLine().split(" ");
            if (userInput.length == 3) {
                if (userInput[0].equals("start")) {
                    for (int i = 1; i < 3; ++i) {
                        switch (userInput[i]) {
                            case "user":
                            case "easy":
                            case "medium":
                            case "hard":
                                break;
                            default:
                                throw new IllegalArgumentException("Bad parameters!");
                        }
                        return setPlayers(new String[] {userInput[1], userInput[2]});
                    }
                } else {
                    throw new IllegalArgumentException("Bad parameters!");
                }
            } else if (userInput.length == 1) {
                if (userInput[0].equals("exit")) {
                    exit = true;
                    return null;
                }
            }
            throw new IllegalArgumentException("Bad parameters!");
    }

    public static int[] userInputPlay() {
        String[] userInput;
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextLine().split(" ");
        if (userInput.length == 2) {
            try {
                int x = Integer.parseInt(userInput[0]) - 1;
                int y = Integer.parseInt(userInput[1])- 1;
                if (!(x >= 0 && x <= 2 && y >= 0 && y <= 2 )) {
                    throw new IllegalArgumentException ("Coordinates should be from 1 to 3!");
                }
                return new int[] {x, y};
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Bad parameters!");
            }
        } else if (userInput.length == 1) {
            if (userInput[0].equals("exit")) {
                exit = true;
                return null;
            }
        }
        throw new IllegalArgumentException("Bad parameters!");
    }
    public static Player[] setPlayers (String[] input) {
        Player[] players = new Player[2];
        for (int i = 0; i < 2; ++i) {
            if (input[i].equals("user")) {
                players[i] = new HumanPlayer();
            } else {
                players[i] = new AI(input[i]);
            }
        }
        return players;
    }
    //converts the position of map.toString() to coordination in field[][]
    public static int[] posToCoord(int inStringPos) {
        if (inStringPos == 0 || inStringPos == 1 || inStringPos == 2) {
            return new int[] {0, inStringPos};
        } else if (inStringPos == 3 || inStringPos == 4 || inStringPos == 5) {
            return new int[] {1, inStringPos - 3};
        } else {
            return new int[] {2, inStringPos - 6};
        }
    }
}
