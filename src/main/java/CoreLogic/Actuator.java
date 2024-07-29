package CoreLogic;

import static CoreLogic.ArduinoFacade.getArduinoFacade;

public class Actuator {

    //Variable declarations
    public static int position;
    final static char initilizer = 2;
    private final double strokeLength = 2.806;
    private final double inchPerStep = .010064453125;
    static ArduinoInterface ardFac = getArduinoFacade();
    public Actuator() {};

    /* Move the actuator to the new position via the ArduinoFacade class. Sleep 800 ms between writing
     * to serial to allow the arduino to process.
     */
    public void setPosition(int newPosition) throws InterruptedException {
        position = newPosition;
        ardFac.initial(initilizer);
        Thread.sleep(800);
        ardFac.newPosition(position);
    }

    /* This method confirms that a new position is not outside the actuators bounds. Current bounds are
     *  Full retraction = 0, Full extension = 255.
     */
    public boolean checkPositionBounds(int position, int direction) {

        if (position > 255 || position < 0) {
            return true;
        } else {
            return false;
        }
    }

    //Calculate and return the new length of the piece after a cut has been made.
    public double getCutLength() { return strokeLength - (inchPerStep * position); }

}
