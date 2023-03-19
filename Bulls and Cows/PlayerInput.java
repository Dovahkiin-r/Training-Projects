import java.util.Scanner;
public class PlayerInput {
    static StringBuilder possibleDigits = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");
    private int digit;
    private int totalSize;
    private char[] guessedCode;

    PlayerInput () {
        System.out.println("Please, enter the secret code's length:");
        this.digit = Integer.parseInt(getInput());
        System.out.println("Please, enter the number of possible symbols in the code:");
        this.totalSize = Integer.parseInt(getInput());
        if (digit == 0) {
            throw new IllegalArgumentException("Error: can not generate a code with 0 digits");
        }
        if (totalSize > 36 || digit > totalSize) {
            String message = "Error: can't generate a secret number with a length of " +
                    digit + " because there aren't enough unique digits.";
            throw new IllegalArgumentException(message);
        }
        possibleDigits.delete(this.totalSize, 37);
    }
    public void setGuessedCode() {
        this.guessedCode = getInput().toCharArray();
        if (guessedCode.length != this.digit) {
            String message = "Error: entered Code size is different than the secret code";
            throw new IllegalArgumentException(message);
        }
    }
    public char getGuessedCodeDigit(int index) {
        if (possibleDigits.indexOf(Character.toString(this.guessedCode[index])) == -1) {
            throw new IllegalArgumentException("Error: input have wrong character in it");
        }
        return this.guessedCode[index];
    }

    public int getDigit() {
        return digit;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
