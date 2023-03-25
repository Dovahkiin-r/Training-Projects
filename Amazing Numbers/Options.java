
enum Options {
    EVEN(false), ODD(false), BUZZ(false), DUCK(false),
    PALINDROMIC(false), GAPFUL(false), SPY(false), SQUARE(false),
    SUNNY(false), JUMPING(false), HAPPY(false), SAD(false);
    private boolean value;
    private Options (boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
    public void setValue(boolean value) {
        this.value = value;
    }
}