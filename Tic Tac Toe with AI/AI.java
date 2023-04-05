package tictactoe;

import java.util.Objects;
import java.util.Random;

public class AI extends Player {

    AI (String difficulty) {
        super(difficulty);
    }

    public int[] coordsToplay(Map map) {
        System.out.printf ("Making move level \"%s\" %n", super.getName());
        switch (this.getName()) {
            case "easy":
                return this.easyAI(map);
            case "medium":
                return this.mediumAI(map);
            default: //case "hard":
                return this.hardAI(map);
        }
    }

    private int[] easyAI(Map map) {
        Random random = new Random();
        boolean finished = false;
        int x = 0;
        int y = 0;
        while (!finished) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            if (map.atCoord(x, y) == 0) {
                finished = true;
            }
        }
        return new int[] {x, y};
    }
    private int[] mediumAI(Map map) {
        String mapString = map.toString();
        char opSign = this.getSign() == 'X' ? 'O' : 'X';
        for (int[] cond : Functions.winCond) {
            if (mapString.charAt(cond[2]) == '_' &&
                    ((mapString.charAt(cond[0]) == opSign && mapString.charAt(cond[1]) == opSign) ||
                    (mapString.charAt(cond[0]) == this.getSign() && mapString.charAt(cond[1]) == this.getSign()))) {
                return Functions.posToCoord(cond[2]);
            } else if (mapString.charAt(cond[1]) == '_' &&
                    ((mapString.charAt(cond[0]) == opSign && mapString.charAt(cond[2]) == opSign) ||
                    (mapString.charAt(cond[0]) == this.getSign() && mapString.charAt(cond[2]) == this.getSign()))) {
                return Functions.posToCoord(cond[1]);
            } else if (mapString.charAt(cond[0]) == '_' &&
                    ((mapString.charAt(cond[1]) == opSign && mapString.charAt(cond[2]) == opSign) ||
                    (mapString.charAt(cond[1]) == this.getSign() && mapString.charAt(cond[2]) == this.getSign()))) {
                return Functions.posToCoord(cond[0]);
            }
        }
        return easyAI(map);
    }

    private int[] hardAI(Map map) {
        Map testMap = new Map(map.toString());
        int highScore = -10000;
        int score;
        int bestX = 100;
        int bestY = 100;
        char empty = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (map.atCoord(i, j) == 0) {
                    map.addAtCoord(i, j, this.getSign());
                    score = minMax(map, false, -1000 , 1000);
                    if (highScore < score) {
                        highScore = score;
                        bestX = i;
                        bestY = j;
                    }
                    map.addAtCoord(i, j, empty);
                }
            }
        }
        return new int[]{bestX, bestY};
    }

    private int minMax(Map map, boolean turn, int alpha, int beta) {
        //map.printMap();
        char empty = 0;
        int result;
        if (map.isGameOver()) {
            if (turn) {
                return -10;
            } else {
                return 10;
            }
        } else if (map.isGameDraw()) {
            return 0;
        } else {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (map.atCoord(i, j) == 0) {
                        if (turn) {
                            map.addAtCoord(i, j, this.getSign());
                            result = minMax(map, false, alpha, beta);
                            map.addAtCoord(i, j, empty);
                            if (result > alpha) {
                                alpha = result;
                            } else {break;}
                        } else {
                            char sign = this.getSign() == 'X' ? 'O' : 'X';
                            map.addAtCoord(i, j, sign);
                            result = minMax(map, true, alpha, beta);
                            map.addAtCoord(i, j, empty);
                            if (result < beta) {
                                beta = result;
                            } else {break;}
                        }
                    }
                }
            }
        }
        if (turn) {
            return alpha;
        } else {
            return beta;
        }
    }
}
