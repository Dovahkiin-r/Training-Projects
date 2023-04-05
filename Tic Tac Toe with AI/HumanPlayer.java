package tictactoe;
class HumanPlayer extends Player {

    HumanPlayer() {
        super();
    }

    public int [] coordsToplay(Map map) {
        int [] input = new int[2];
        String userInput = "";
        System.out.print ("Enter the coordinates: ");
        input = Functions.userInputPlay();
        if (map.atCoord(input[0], input[1]) != 0) {
            throw new IllegalArgumentException ("Bad parameters!");
        }
        return input;
    }

}
