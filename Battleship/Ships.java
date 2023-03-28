public class Ships extends MapObjects {
    private String name;
    private int health = 120;
    private int size;
    private boolean destroyed = false;
    Ships(char name) {
        super('O');
        switch (name) {
            case 'A':
                this.name = "Aircraft Carrier";
                this.size = 5;
                break;
            case 'B':
                this.name = "Battleship";
                this.size = 4;
                break;
            case 'S':
                this.name = "Submarine";
                this.size = 3;
                break;
            case 'C':
                this.name = "Cruiser";
                this.size = 3;
                break;
            case 'D':
                this.name = "Destroyer";
                this.size = 2;
                break;
        }
    }
    public String getName() {return name;}

    public int getSize() {
        return this.size;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean gotHit() {
        this.health -= 120 / this.size;
        if (this.health == 0) {
            this.destroyed = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSize (int[] start, int[] end) {
        int[] length = Functions.lineLength(start, end);
        boolean check;
        if (length[0] < length[1]) {
            check = length[1] == this.size;
        } else {
            check = length[0] == this.size;
        }
        return check;
    }
}
