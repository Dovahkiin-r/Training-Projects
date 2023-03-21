import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        String path = ".\\rating.txt";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.next();
        System.out.printf("%nHello, %s%n", name);
        Player player = new Player(name, path);
        String[] allSigns = inputAllSigns();
        System.out.println("Okay, let's start");
        int computerSignValue;
        String result;
        do {
            try {
                player.setSign(getInput(allSigns));
                if (player.getSign().equals("!exit")) {
                    break;
                } else if (player.getSign().equals("!rating")) {
                    System.out.printf("Your rating: %d%n",player.getScore());
                    continue;
                }
                computerSignValue = computerPlay(allSigns);
                result = compareSigns(player, computerSignValue, allSigns);
                System.out.printf(result, allSigns[computerSignValue]);
            } catch (IllegalArgumentException iae) {
                System.out.println("Invalid input");
                continue;
            }
        } while (!player.getSign().equals("!exit"));
        System.out.println("Bye!");
    }

    public static String getInput(String[] allSigns) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        boolean rightInput = false;
        if (input.equals("!exit") || input.equals("!rating")){
            rightInput = true;
        } else {
            rightInput = isSign(input, allSigns);
        }
        if (!rightInput) {
            throw new IllegalArgumentException();
        }
        return input;
    }
    public static String[] inputAllSigns() {
        Scanner sc = new Scanner(System.in);
        String[] allSigns;
        String input = sc.nextLine();
        if (input.length() > 0) {
            allSigns = input.split(",");
        } else {
            allSigns = new String[3];
            allSigns[0] = "rock";
            allSigns[1] = "paper";
            allSigns[2] = "scissors";
        }
        return allSigns;
    }
    public static int getSignValue (String sign, String[] allSigns) {
        int index = -1;
        for (int i = 0; i < allSigns.length; ++i) {
            if (sign.equals(allSigns[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static boolean isSign (String sign, String[] allSigns) {
        boolean isSign = false;
        for (String index : allSigns) {
            if (index.equals(sign)) {
                isSign = true;
                break;
            }
        }
        return isSign;
    }
    public static String compareSigns (Player player, int computerSignValue, String[] allSigns) {
        String wonMassege = "Well done. The computer chose %s and failed%n";
        String drawMassege = "There is a draw (%s)%n";
        String lostMassege = "Sorry, but the computer chose %s%n";
        int medianPos = (allSigns.length - 1) / 2;
        int playerSignValue = getSignValue(player.getSign(), allSigns);
        int vl = (playerSignValue + medianPos) % (allSigns.length);
        if (playerSignValue == computerSignValue) {
            player.addScore(50);
            return drawMassege;
        } else {
            if (playerSignValue + medianPos == vl) {
                if (computerSignValue > playerSignValue && computerSignValue <= playerSignValue + medianPos) {
                    return lostMassege;
                } else {
                    player.addScore(100);
                    return wonMassege;
                }
            } else if (computerSignValue <= vl || playerSignValue < computerSignValue) {
                return lostMassege;
            } else {
                player.addScore(100);
                return wonMassege;
            }
        }
    }
    public static int computerPlay(String[] allSigns) {
        //computer play
        Random random = new Random();
        int randomNum = random.nextInt(allSigns.length);
        return randomNum;
    }
}
