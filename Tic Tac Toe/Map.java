class Map {
    private MapObject [][] field = new MapObject [3][3];

    Map () {
        for (int x = 0; x < 3; ++x) {
            for ( int y = 0; y < 3; ++y) {
                this.field [x][y] = MapObject.EMPTHY;
            }
        }
    }

    public MapObject atCoord (int x, int y) {
        return this.field [x][y];
    }

    public void addAtCoord (int x, int y, MapObject obj) throws IllegalArgumentException {
        switch (this.atCoord(x, y)) {
            case X:
            case O:
                throw new IllegalArgumentException ("This cell is occupied! Choose another one!");
            default:
                this.field [x][y] = obj;
        }
    }

    public String toString () {
        String map = "";
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                switch (this.atCoord(x, y)) {
                    case X:
                        map += "X";
                        break;
                    case O:
                        map += "O";
                        break;
                    default:
                        map += " ";
                }
            }
        }
        return map;
    }
}
