public abstract class MapObjects {
    private char type;

    public MapObjects(char type) {
        this.type = type;
    }
    public String print() {
        return Character.toString(this.type);
    }
}
