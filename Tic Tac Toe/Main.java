public class Main {
    public static void main(String[] args) {
        //build the map and players
        Map map = new Map();
        Player [] players = new Player [2];
        players [0] = new Player ("Player 1", MapObject.X);
        players [1] = new Player ("Player 2", MapObject.O);

        //print the map
        String stringMap = map.toString();
        System.out.println("---------");
        System.out.println("| "+stringMap.charAt(0)+" "+stringMap.charAt(1)+" "+stringMap.charAt(2)+" |");
        System.out.println("| "+stringMap.charAt(3)+" "+stringMap.charAt(4)+" "+stringMap.charAt(5)+" |");
        System.out.println("| "+stringMap.charAt(6)+" "+stringMap.charAt(7)+" "+stringMap.charAt(8)+" |");
        System.out.println("---------");

        //main game loop
        int turn = 0;
        int [] input = new int [2];
        int [][] winCond = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        boolean gameEnd = false;
        String winer = "";

        while(!gameEnd && turn < 9) {
            try {
                input =  players [turn % 2].getInput();
                map.addAtCoord (input[0], input[1], players [turn % 2].getSign());
            } catch (IllegalArgumentException iae) {
                System.out.println (iae.getMessage());
                continue;
            }
            //print the new map
            stringMap = map.toString();
            System.out.println("---------");
            System.out.println("| "+stringMap.charAt(0)+" "+stringMap.charAt(1)+" "+stringMap.charAt(2)+" |");
            System.out.println("| "+stringMap.charAt(3)+" "+stringMap.charAt(4)+" "+stringMap.charAt(5)+" |");
            System.out.println("| "+stringMap.charAt(6)+" "+stringMap.charAt(7)+" "+stringMap.charAt(8)+" |");
            System.out.println("---------");

            //check game state
            for (int [] cond : winCond) {
                if (stringMap.charAt(cond [0]) == stringMap.charAt(cond [1]) &&
                        stringMap.charAt(cond [0]) == stringMap.charAt(cond [2])) {
                    if (stringMap.charAt(cond [0]) != ' '){
                        gameEnd = true;
                        winer += stringMap.charAt(cond [0]) +" wins";
                        break;
                    }
                }
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