package CoreLogic;

public class Piece {
    public static double startingLength = -1;
    public static double currLength = -1;
    public static double targetLength = -1;
    public static double timeToMake = 0;

    public Piece() {}

    public static void resetLengths() {
        startingLength = -1;
        currLength = -1;
        targetLength = -1;
    }


}
