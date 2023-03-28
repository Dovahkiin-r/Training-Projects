public class Map {
    private MapObjects[][] field;

    //constructor
    public Map(int x, int y) {
        this.field = new MapObjects[x][y];
    }

    //place the Ship on the map
    public void placeShipOnMap (int[] start, int[] end, Ships ship) {
        int[] length = Functions.lineLength(start, end);
        Functions.coordSorter(start, end);
        if (length[0] < length[1]) {
            for (int i = start[1]; i <= end[1]; ++i) {
                this.field[start[0]][i] = ship;
            }
        } else {
            for (int i = start[0]; i <= end[0]; ++i) {
                this.field[i][start[1]] = ship;
            }
        }
    }
    //place on map
    public void placeOnMap(int[] coord, MapObjects obj) {
        this.field[coord[0]][coord[1]] = obj;
    }
    //shot at the coodination
    public void shootAtCoord(int[] coord) {
        if (this.getObjectAtCoord(coord[0], coord[1]) == null) {
            placeOnMap(coord, new Missed());
            System.out.println("\nYou missed!\n");
        } else {
            boolean isDestroyed = false;
            if (this.getObjectAtCoord(coord[0], coord[1]) instanceof Ships ) {
                isDestroyed = ((Ships) this.getObjectAtCoord(coord[0], coord[1])).gotHit();
            }
            placeOnMap(coord, new Hit());
            if (isDestroyed) {
                System.out.println("\nYou sank a ship! Specify a new target:\n");
            } else {
                System.out.println("\nYou hit a ship!\n");
            }
        }
    }

    //checks if the given coordination is on the map
    public boolean isCoordOnMap (int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        } else {
            return x < this.field.length && y < this.field.length;
        }
    }

    //checks if one tile around the coord is occupied
    public boolean checkAroundCoord(int[] start, int[] end) {
        int[] length = Functions.lineLength(start, end);
        Functions.coordSorter(start, end);
        int[] check = {-1, 0, 1};
        boolean isEmpthy = true;
        if (length[0] < length[1]) {
            for (int i = start[1]; i <= end[1]; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (this.isCoordOnMap(start[0], i + check[j]) &&
                            this.getObjectAtCoord(start[0], i + check[j]) != null) {
                        isEmpthy = false;
                        break;
                    }
                    if (this.isCoordOnMap(start[0] + check[j], i) &&
                            this.getObjectAtCoord(start[0] + check[j], i) != null) {
                        isEmpthy = false;
                        break;
                    }
                }
            }
        } else {
            for (int i = start[0]; i <= end[0]; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (this.isCoordOnMap(i + check[j], start[1]) &&
                            this.getObjectAtCoord(i + check[j], start[1]) != null) {
                        isEmpthy = false;
                        break;
                    }
                    if (this.isCoordOnMap(i, start[1] + check[j]) &&
                            this.getObjectAtCoord(i, start[1] + check[j]) != null) {
                        isEmpthy = false;
                        break;
                    }
                }
            }
        }
        return isEmpthy;
    }
    //returns the object in the given coord
    public MapObjects getObjectAtCoord (int x, int y) {
        if (this.field[x][y] != null) {
            return this.field[x][y];
        } else {
            return null;
        }
    }

    //returns map as a string
    public String toString(boolean fOW) {
        if (!fOW) {
            StringBuilder map = new StringBuilder();
            int col = 1;
            //first line of map
            map.append("  ");
            for (MapObjects[] x : this.field) {
                map = map.append(col).append(" ");
                ++col;
            }
            //other lines of the map
            char lineChar = 'A';
            for (MapObjects[] x : this.field) {
                map.append("\n").append(lineChar).append(" ");
                ++lineChar;
                for (MapObjects y : x) {
                    if (y != null) {
                        map.append(y.print()).append(" ");
                    } else {
                        map.append("~ ");
                    }
                }
            }
            return map.toString();
        } else {
            StringBuilder map = new StringBuilder();
            int col = 1;
            //first line of map
            map.append("  ");
            for (MapObjects[] x : this.field) {
                map = map.append(col).append(" ");
                ++col;
            }
            //other lines of the map
            char lineChar = 'A';
            for (MapObjects[] x : this.field) {
                map.append("\n").append(lineChar).append(" ");
                ++lineChar;
                for (MapObjects y : x) {
                    if (y != null && !(y instanceof  Ships)) {
                        map.append(y.print()).append(" ");
                    } else {
                        map.append("~ ");
                    }
                }
            }
            return map.toString();
        }
    }
}
