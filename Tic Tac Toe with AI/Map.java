package tictactoe;
class Map {
    private char[][] field = new char [3][3];

    Map () {}
    @Deprecated //use for testing
    Map (String input) {
        int i = 0;
        for (int x = 0; x < 3; ++x) {
            for ( int y = 0; y < 3; ++y) {
                switch (input.charAt(i)){
                    case 'O':
                        this.field [x][y] = 'O';
                        break;
                    case 'X':
                        this.field[x][y] = 'X';
                        break;
                    case '_':
                        this.field[x][y] = 0;
                        break;
                }
                ++i;
            }
        }
    }

    public char atCoord (int x, int y) {
        return this.field [x][y];
    }
    public void addAtCoord (int x, int y, char obj){
        this.field [x][y] = obj;
    }

    public boolean isGameDraw() {
        int count = 0;
        for (int i = 0; i < 3; ++i ) {
            for (int j = 0; j < 3; ++j) {
                if (this.atCoord(i, j) == 0) {
                    ++count;
                }
            }
        }
        if(count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isGameOver() {
        String stringMap = this.toString();
        for (int[] cond : Functions.winCond) {
            if (stringMap.charAt(cond[0]) == stringMap.charAt(cond[1]) &&
                    stringMap.charAt(cond[0]) == stringMap.charAt(cond[2])) {
                if (stringMap.charAt(cond[0]) != '_') {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString () {
        StringBuilder map = new StringBuilder();
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                switch (this.atCoord(x, y)) {
                    case 'X':
                        map.append("X");
                        break;
                    case 'O':
                        map.append("O");
                        break;
                    default:
                        map.append("_");
                }
            }
        }
        return map.toString();
    }
    public void printMap() {
        StringBuilder stringMap = new StringBuilder();
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                switch (this.atCoord(x, y)) {
                    case 'X':
                        stringMap.append("X");
                        break;
                    case 'O':
                        stringMap.append("O");
                        break;
                    default:
                        stringMap.append(" ");
                }
            }
        }
        System.out.println("---------");
        System.out.println("| " + stringMap.charAt(0) + " " + stringMap.charAt(1) + " " + stringMap.charAt(2) + " |");
        System.out.println("| " + stringMap.charAt(3) + " " + stringMap.charAt(4) + " " + stringMap.charAt(5) + " |");
        System.out.println("| " + stringMap.charAt(6) + " " + stringMap.charAt(7) + " " + stringMap.charAt(8) + " |");
        System.out.println("---------");
    }
    @Deprecated //use for testing, change the turn % 2 in main methode with this
    public int playerTurn(){
        int playerO = 0;
        int playerX = 0;
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                switch (this.atCoord(x, y)) {
                    case 'X':
                        ++playerX;
                        break;
                    case 'O':
                        ++playerO;
                        break;
                }
            }
        }
        if (playerO < playerX) {
            return 1;
        } else {
            return 0;
        }
    }

}
