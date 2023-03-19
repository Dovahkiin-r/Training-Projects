import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        try {
            //ask user the start inputs and save them
            PlayerInput input = new PlayerInput();
            StringBuilder bulls = new StringBuilder();
            StringBuilder cows = new StringBuilder();
            char[] secretCode = generateCode(input);
            for (int i = 0; i < input.getDigit(); ++i) {
                bulls.append("*");
            }
            if (input.getTotalSize() <= 10) {
                cows.append("0-").append(PlayerInput.possibleDigits.charAt(input.getTotalSize() - 1));
            } else {
                cows.append("0-9, a-").append(PlayerInput.possibleDigits.charAt(input.getTotalSize() - 1));
            }
            System.out.println("The secret code is prepared: "+ bulls +" ("+cows+").");
            System.out.println("Okay, let's start a game!");

            int turn = 1;
            boolean end = false;
            while (!end) {
                System.out.println("Turn "+turn+":");
                input.setGuessedCode();
                int[] result = dataProcess(input, secretCode);
                if (result[0] == input.getDigit()) {
                    end = true;
                    bulls.delete(0,bulls.length()).append(result[0] + " bulls");
                    cows.delete(0,cows.length());
                } else {
                    if (result[0] == 0 && result[1] == 0) {
                        bulls.delete(0,bulls.length()).append("None.");
                    } else {
                        bulls.delete(0,bulls.length()).append(result[0] + " bulls and ");
                        cows.delete(0,bulls.length()).append(result[1] + " cows");
                    }
                }
                System.out.println("Grade: "+bulls.toString() + cows.toString());
                ++turn;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        } catch (NumberFormatException iae) {
            System.out.println("Error: you must enter a number!!");
        }  catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }

    //result[0] is bulls, result[1] is cows
    public static int[] dataProcess(PlayerInput input, char[] code) {
        int[] result = new int[2];
        for (int i = 0; i < input.getDigit(); ++i) {
            for (int j = 0; j < input.getDigit(); ++j) {
                if (code[i] == input.getGuessedCodeDigit(j)) {
                    if (i == j) {
                        ++result[0];
                    } else {
                        ++result[1];
                    }
                }
            }
        }
        return result;
    }

    public static char[] generateCode(PlayerInput input) {
        StringBuilder possibleDigits = new StringBuilder(PlayerInput.possibleDigits);
        StringBuilder secretCode = new StringBuilder();
        Random random = new Random();
        int max = possibleDigits.length();
        int randomIndex = 0;
        for (int i = 0; i < input.getDigit(); ++i, --max) {
            randomIndex = random.nextInt(max);
            secretCode.append(possibleDigits.charAt(randomIndex));
            possibleDigits.deleteCharAt(randomIndex);
        }
        System.out.println(secretCode.toString());
        return secretCode.toString().toCharArray();
    }
}