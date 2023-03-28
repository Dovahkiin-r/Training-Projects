import java.util.Scanner;
import static battleship.Functions.*;
public class Main {
    static final int MAP_SIZE = 10;
    public static void main(String[] args) {
        //build the map
        Map[] maps = {new Map(MAP_SIZE, MAP_SIZE), new Map(MAP_SIZE, MAP_SIZE)};
        //build players
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        //build 5 ships for each player
        Ships[] player1Ships = {new Ships('A'), new Ships('B'), new Ships('S'),
                new Ships('C'), new Ships('D')};
        Ships[] player2Ships = {new Ships('A'), new Ships('B'), new Ships('S'),
                new Ships('C'), new Ships('D')};
        Ships[][] allShips = {player1Ships, player2Ships};
        //ask players to place ships on the map
        Scanner sc = new Scanner(System.in);
        for (int index = 0; index < players.length; ++index) {
            System.out.printf("%s, place your ships on the game field%n%n", players[index].getName());
            System.out.println(maps[index].toString(false));
            int i = 0;
            String input;
            while (i < 5) {
                //get user input
                System.out.printf("%nEnter the coordinates of the %s (%d cells):%n%n", allShips[index][i].getName(),
                        allShips[index][i].getSize());
                String[] userInput = sc.nextLine().split(" ");
                int[] start = coordConverter(userInput[0]);
                int[] end = coordConverter(userInput[1]);
                //check input
                boolean sizeCheck = allShips[index][i].checkSize(start, end);
                boolean isOnMap = maps[index].isCoordOnMap(start[0], start[1]) && maps[index].isCoordOnMap(end[0], end[1]);
                boolean directionCheck = lineDirectionCheck(start, end);
                boolean isHerePlaceable = maps[index].checkAroundCoord(start, end);
                if (sizeCheck && isOnMap && directionCheck && isHerePlaceable) {
                    maps[index].placeShipOnMap(start, end, allShips[index][i]);
                    System.out.println("\n" + maps[index].toString(false));
                    ++i;
                } else {
                    if (!sizeCheck) {
                        System.out.printf("%nError, Wrong length of the %s! Try again:%n", allShips[index][i].getName());
                    } else if (!isOnMap || !directionCheck) {
                        System.out.println("\nError! Wrong ship location! Try again:\n");
                    } else {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    }
                }
            }
            System.out.println("\nPress Enter and pass the move to another player");
            input = sc.nextLine();
            System.out.println("...");
        }
        boolean areShipsLeft = true;
        System.out.println("\nThe game starts!\n");
        do {
            for (int i = 0; i < players.length && areShipsLeft; ++i) {
                int j = i == 0 ? 1 : 0;
                System.out.println(maps[j].toString(true));
                System.out.println("---------------------");
                System.out.println(maps[i].toString(false) + "\n");
                System.out.printf("%s, it's your turn:%n%n", players[i].getName());
                boolean isOnMap;
                int[] coord = coordConverter(sc.nextLine());
                isOnMap = maps[j].isCoordOnMap(coord[0], coord[1]);
                if (isOnMap) {
                    maps[j].shootAtCoord(coord);
                } else {
                    System.out.println("Error! Out of map bound. Try again:\n");
                }
                areShipsLeft = false;
                for (int index = 0; index < allShips[j].length; ++index) {
                    if (!allShips[j][index].isDestroyed()) {
                        areShipsLeft = true;
                        System.out.println("Press Enter and pass the move to another player\n");
                        String input = sc.nextLine();
                        break;
                    }
                }
            }
        } while (areShipsLeft);
        System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
    }
}
