package CoreLogic;

import static CoreLogic.Constants.negativeOne;

public class Piece {

    // Variable declarations
    public static double startingLength = negativeOne;
    public static double currLength = negativeOne;
    public static double targetLength = negativeOne;
    public Piece() {}

    //Reset all piece lengths.
    public static void resetLengths() {
        startingLength = negativeOne;
        currLength = negativeOne;
        targetLength = negativeOne;
    }




}
