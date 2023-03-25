import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        showPropertiesMenu();
        String userInput;
        Options options[] = Options.values();
        boolean isNatural;
        long num1 = -1;
        long num2 = 0;
        String[] input;
        do {
            isNatural = false;
            System.out.print("\nEnter a request: ");
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            input = userInput.split(" ");
            if (input.length == 0) {
                showPropertiesMenu();
                continue;
            } else if (input.length == 1) {
                try {
                    num1 = Long.valueOf(userInput);
                } catch (NumberFormatException nfe) {
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");
                }
                if (!isNatural(num1)) {
                    if (num1 == 0) {
                        System.out.println("\nGoodbye!");
                        break;
                    } else {
                        System.out.println("\nThe first parameter should be a natural number or zero.\n");
                        continue;
                    }
                }
                numberOptions(num1);
                System.out.printf("\nProperties of %d%n", num1);
                for (Options option : options) {
                    System.out.printf(option.name() + ": %b%n", option.getValue());
                    option.setValue(false);
                }
            } else if (input.length == 2) {
                try {
                    num1 = Long.valueOf(input[0]);
                } catch (NumberFormatException nfe) {
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");
                }
                try {
                    num2 = Long.valueOf(input[1]);
                } catch (NumberFormatException nfe) {
                    System.out.println("\nThe second parameter should be a natural number.\n");
                }
                if (!isNatural(num1)) {
                    if (num1 == 0) {
                        System.out.println("\nGoodbye!");
                        break;
                    } else {
                        System.out.println("\nThe first parameter should be a natural number or zero.\n");
                        continue;
                    }
                }
                if (!isNatural(num2)) {
                    System.out.println("\nThe second parameter should be a natural number.\n");
                    continue;
                }
                for (long i = num1; i < num1 + num2; ++i) {
                    System.out.printf(numberAnalyse(i).toString(), i);
                }
                System.out.println();
            } else {
                try {
                    num1 = Long.valueOf(input[0]);
                } catch (NumberFormatException nfe) {
                    System.out.println("\nThe first parameter should be a natural number or zero.\n");
                    continue;
                }
                try {
                    num2 = Long.valueOf(input[1]);
                } catch (NumberFormatException nfe) {
                    System.out.println("\nThe second parameter should be a natural number.\n");
                    continue;
                }
                Options[] properties = new Options[input.length - 2];
                Options[] negProperties = new Options[input.length - 2];
                String negInput = "";
                StringBuilder massege = new StringBuilder("\nThe properties [");
                String[] wrongProperties = new String[input.length - 2];
                boolean hasWrongProp = false;
                int wrongCount = 0;
                int negProps = 0;
                int normProps = 0;
                for (int i = 0; i < properties.length; ++i) {
                    if (input[i + 2].charAt(0) == '-') {
                        negInput = input[i + 2].substring(1);
                        try {
                            negProperties[negProps] = Options.valueOf(negInput.toUpperCase());
                            ++negProps;
                        } catch (IllegalArgumentException iae) {
                            wrongProperties[i] = negInput;
                            hasWrongProp = true;
                            ++wrongCount;
                            continue;
                        }
                    } else {
                        try {
                            properties[normProps] = Options.valueOf(input[i + 2].toUpperCase());
                            ++normProps;
                        } catch (IllegalArgumentException iae) {
                            wrongProperties[i] = input[i + 2];
                            hasWrongProp = true;
                            ++wrongCount;
                            continue;
                        }
                    }
                }
                if (hasWrongProp) {
                    if (wrongCount == 1) {
                        System.out.printf("\nThe property [%s] is wrong.\n", wrongProperties[0]);
                    } else {
                        for (int i = 0; i < wrongCount; ++i) {
                            massege.append(wrongProperties[i]).append(", ");
                        }
                        massege.delete(massege.length() - 1, massege.length()).append("] are wrong.\n");
                        System.out.print(massege);
                    }
                    massege.delete(0, massege.length()).append("Available properties: [");
                    for (Options option : options) {
                        massege.append(option.name()).append(" ,");
                    }
                    massege.deleteCharAt(massege.length() - 1).append("]");
                    System.out.println(massege + "\n");
                    continue;
                } else {
                    String[] mutProps = mutCheck(negProperties);
                    String[] mutProps2 = mutCheck(properties);
                    boolean breakLoop = false;
                    for (Options prop : properties) {
                        for (Options negProp : negProperties) {
                            if (prop != null || negProp != null) {
                                if (prop == negProp) {
                                    System.out.printf("The request contains mutually exclusive properties: [%s, -%s]%n",
                                            prop.name().toLowerCase(), negProp.name().toLowerCase());
                                    System.out.println("There are no numbers with these properties.");
                                    breakLoop = true;
                                    break;
                                }
                            }
                        }
                        if (breakLoop) {
                            break;
                        }
                    }
                    if (breakLoop) {
                        continue;
                    }
                    if (mutProps[0] != null || mutProps2[0] != null) {
                        if (mutProps2 != null) {
                            System.out.printf("The request contains mutually exclusive properties: [%s, %s]%n",
                                    mutProps[0], mutProps[1]);
                            System.out.println("There are no numbers with these properties.");
                        }
                        if (mutProps != null) {
                            System.out.printf("The request contains mutually exclusive properties: [-%s, -%s]%n",
                                    mutProps[0], mutProps[1]);
                            System.out.println("There are no numbers with these properties.");
                        }
                    } else {
                        int counter = 0;
                        boolean hasTheProps = true;
                        boolean hasTheNegProps = false;
                        long[] foundNumbers = new long[(int) num2];
                        for (long i = num1; counter < num2; ++i) {
                            for (Options prop : properties) {
                                if (prop != null) {
                                    prop.setValue(false);
                                }
                            }
                            for (Options prop : negProperties) {
                                if (prop != null) {
                                    prop.setValue(false);
                                }
                            }
                            hasTheProps = true;
                            hasTheNegProps = false;
                            Options.EVEN.setValue(false);
                            Options.ODD.setValue(false);
                            for (Options property : properties) {
                                if (property != null) {
                                    numberSpecOption(i, property);
                                    if (!property.getValue()) {
                                        hasTheProps = false;
                                        break;
                                    }
                                } else {break;}
                            }
                            for (Options property : negProperties) {
                                if (property != null) {
                                    numberSpecOption(i, property);
                                    if (property.getValue()) {
                                        hasTheNegProps = true;
                                        break;
                                    }
                                } else {break;}
                            }
                            if (hasTheProps && !hasTheNegProps) {
                                foundNumbers[counter] = i;
                                ++counter;
                                System.out.printf(numberAnalyse(i).toString(), i);
                                System.out.println();
                            }
                        }
                    }
                }
            }
        } while (true);
    }

    //check all options
    public static void numberOptions(long number) {
        oddOrEven(number);
        isBuzz(number);
        isDuck(number);
        isPalindromic(number);
        isGapful(number);
        isSpy(number);
        isSunny(number);
        isSquare(number);
        isJumping(number);
        isHappy(number);
        isSad(number);
    }
    public static StringBuilder numberAnalyse (long number) {
        Options options[] = Options.values();
        StringBuilder massege = new StringBuilder("");
        numberOptions(number);
        massege.delete(0, massege.length()).append("%n%d is ");
        for (Options otherOption : options) {
            if (otherOption.getValue() == true) {
                massege.append(otherOption.name().toLowerCase() + ", ");
                otherOption.setValue(false);
            }
        }
        return  massege;
    }
    public static void numberSpecOption (long number ,Options option) {
        switch (option) {
            case ODD:
            case EVEN:
                oddOrEven(number);
                break;
            case BUZZ:
                isBuzz(number);
                break;
            case DUCK:
                isDuck(number);
                break;
            case PALINDROMIC:
                isPalindromic(number);
                break;
            case GAPFUL:
                isGapful(number);
                break;
            case SPY:
                isSpy(number);
                break;
            case SUNNY:
                isSunny(number);
                break;
            case SQUARE:
                isSquare(number);
                break;
            case JUMPING:
                isJumping(number);
                break;
            case SAD:
                isSad(number);
                break;
            case HAPPY:
                isHappy(number);
                break;
        }
    }
    // mut check
    public static String[] mutCheck(Options[] properties) {
        boolean evenOdd = false;
        boolean sunnnySquare = false;
        boolean duckSpy = false;
        String[] mutProps = new String[2];
        for (Options property : properties) {
            if (property == Options.ODD) {
                for (Options prop : properties) {
                    if (prop == Options.EVEN) {
                        evenOdd = true;
                        mutProps[0] = "even";
                        mutProps[1] = "odd";
                        break;
                    }
                }
            }
            if (property == Options.SUNNY) {
                for (Options prop : properties) {
                    if (prop == Options.SQUARE) {
                        sunnnySquare = true;
                        mutProps[0] = "sunny";
                        mutProps[1] = "square";
                        break;
                    }
                }
            }
            if (property == Options.DUCK) {
                for (Options prop : properties) {
                    if (prop == Options.SPY) {
                        duckSpy = true;
                        mutProps[0] = "duck";
                        mutProps[1] = "spy";
                        break;
                    }
                }
            }
            if (property == Options.HAPPY) {
                for (Options prop : properties) {
                    if (prop == Options.SAD) {
                        duckSpy = true;
                        mutProps[0] = "duck";
                        mutProps[1] = "spy";
                        break;
                    }
                }
            }
        }
        return mutProps;
    }
    //properties menu
    public static void showPropertiesMenu() {
        System.out.println("Welcome to Amazing Numbers!\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;" +
                "- a property preceded by minus must not be present in numbers;" +
                "- separate the parameters with one space; \n" +
                "- enter 0 to exit.\n");
    }
    //checks if the number is natural
    public static boolean isNatural (long number) {
        if (number < 1) {
            return false;
        } else {
            return true;
        }
    }

    //checks if the number is odd or even
    public static void oddOrEven(long number) {
        if (number % 2 == 0) {
            Options.EVEN.setValue(true);
        } else {
            Options.ODD.setValue(true);
        }
    }

    //Checks if the number is Buzz
    public static void isBuzz(long number) {
        if (number % 10 == 7 || number % 7 == 0) {
            Options.BUZZ.setValue(true);
        }
    }

    //Checks if the number is Duck
    public static void isDuck(long number) {
        String numString = Long.toString(number);
        if (numString.indexOf('0') != -1) {
            Options.DUCK.setValue(true);
        }
    }

    //Checks if the nmmber is Palindromic
    public static void isPalindromic(long number) {
        String numString = Long.toString(number);
        Options.PALINDROMIC.setValue(true);;
        for (int i = 0; i < (numString.length() / 2); ++i) {
            if (numString.charAt(i) != numString.charAt(numString.length() - 1 -i)) {
                Options.PALINDROMIC.setValue(false);
                break;
            }
        }
    }
    //Checks if the nmmber is Gapful
    public static void isGapful(long number) {
        String numString = Long.toString(number);
        int numLength = numString.length();
        if (numLength > 2) {
            int firstDigit = (int) (number / ((long) Math.pow(10, (numLength - 1))));
            int lastDigit = (int) (number % 10);
            if (number % (firstDigit * 10 + lastDigit) == 0) {
                Options.GAPFUL.setValue(true);
            }
        }
    }
    //check if the number is spy
    public static void isSpy(long number) {
        if (sumDigits(number) == productDigits(number)) {
            Options.SPY.setValue(true);
        }
    }
    public static int sumDigits (long number) {
        int lastDigit = (int) (number % 10);
        long rest = number / 10;
        if (number != 0) {
            return lastDigit + sumDigits(rest);
        } else {
            return 0;
        }
    }
    public static int productDigits (long number) {
        int lastDigit = (int) (number % 10);
        long rest = number / 10;
        if (number != 0) {
            return lastDigit * productDigits(rest);
        } else {
            return 1;
        }
    }
    //checks if the number is square number
    public static void isSquare(long number) {
        if (Math.pow((long) Math.sqrt((long) number), 2) == number) {
            Options.SQUARE.setValue(true);
        }
    }

    //checks if the number is sunny
    public static void isSunny(long number) {
        Options.SQUARE.setValue(false);
        isSquare(number + 1);
        if (Options.SQUARE.getValue() == true) {
            Options.SUNNY.setValue(true);
            Options.SQUARE.setValue(false);
        }
    }

    //checks if the number is jumping
    public static void isJumping(long number) {
        boolean oneStep = true;
        char[] digits = Long.toString(number).toCharArray();
        if (digits.length == 0) {
            Options.JUMPING.setValue(true);
        } else {
            for (int i = 1; i < digits.length; ++i) {
                if (digits[i] + 1 != digits[i - 1] && digits[i] != digits[i - 1] + 1) {
                    oneStep = false;
                }
            }
        }
        if (oneStep) {
            Options.JUMPING.setValue(true);
        }
    }

    //checks if the number is Happy
    public static void isHappy(long number) {
        long pow = powDigits(number);
        do {
            pow = powDigits(pow);
        } while (pow != 4 && pow != 1);
        if (pow == 1) {
            Options.HAPPY.setValue(true);
        }
    }

    //checks if the number is sad
    public static void isSad(long number) {
        long pow = powDigits(number);
        do {
            pow = powDigits(pow);
        } while (pow != 4 && pow != 1);
        if (pow == 4) {
            Options.SAD.setValue(true);
        }
    }
    public static int powDigits (long number) {
        int lastDigit = (int) (number % 10);
        long rest = number / 10;
        if (number != 0) {
            return lastDigit * lastDigit + powDigits(rest);
        } else {
            return 0;
        }
    }
}