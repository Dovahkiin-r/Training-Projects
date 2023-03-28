public class Functions {
    //converts input coordination to numeric
    public static int[] coordConverter (String input) {
        int [] coord = new int [2];
        coord[0] = input.charAt(0) - 'A';
        coord[1] = Integer.valueOf(input.substring(1)) - 1;
        return coord;
    }
    public static int[] lineLength (int[] startCoord, int[] endCoord) {
        int xLength = Math.abs(endCoord[0] - startCoord[0]) + 1;
        int yLength = Math.abs(endCoord[1] - startCoord[1]) + 1;
        return new int[] {xLength, yLength};
    }
    //checks if 2 given coordination build a horizontal/vertical line. if they build a diagonal line returns false
    public static boolean lineDirectionCheck (int[] start, int[] end) {
        int[] length = Functions.lineLength(start, end);
        return length[0] == 1 || length[1] == 1;
    }
    //make the start points be the smaller coordination
    public static void coordSorter(int[] start, int[] end) {
        int smaller;
        if (start[1] > end [1]) {
            smaller = end[1];
            end[1] = start[1];
            start[1] = smaller;
        }
        if (start[0] > end [0]) {
            smaller = end[0];
            end[0] = start[0];
            start[0] = smaller;
        }
    }

}
