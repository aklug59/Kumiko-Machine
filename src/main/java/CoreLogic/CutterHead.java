package CoreLogic;

import static CoreLogic.ArduinoFacade.currentState;
import static CoreLogic.ArduinoFacade.getArduinoFacade;
import static CoreLogic.Constants.*;

/*
* 800ms delay seems to me minimum functional threshold where the serial on the arduino isn't skipping reads
*
*/
public class CutterHead {
    //Variable declarations
    private static double currAngle = 90;
    private static int motorDirection = 0;
    private static int steps = 0;
    final static char initilizer = 1;
    final static int CCW = -1;
    final static int CW = 1;
    final static double stepsPerDegree = 8.8888;
    static ArduinoInterface ardFac = getArduinoFacade();
    public CutterHead() {}

    //Check the bounds of the new angle to ensure they are between 0 - 90 degrees.
    public static boolean checkAngleBounds(double currAngle) {
        if (currAngle > 90 || currAngle < 0) {
            return false;
        } else {
            return true;
        }
    }

    //Set the CutterHead to a new angle.
    public static void setAngle(double angle) throws InterruptedException {
        //If a connection between the computer and the Arduino has not yet been made, make it.
        if (currentState.equals(CLOSED)) {
            ardFac.openConnection();
            currentState = OPEN;
        }

        /*Send the arduino an integer indicating StepperMotor vs.
        actuator control. 1 = StepperMotor, 2 = actuator. */
        ardFac.initial(initilizer);

        // Set motor direction based on current angle.
        if (angle < currAngle) {
            motorDirection = CCW;
        }
        else if (angle > currAngle){
            motorDirection = CW;
        }

        /*Send the arduino the motor direction followed by the number of steps to move. Sleep 800ms between both
          to allow arduino to process. */
        Thread.sleep(800);
        ardFac.initial(motorDirection);
        Thread.sleep(800);
        steps = (int)Math.round(Math.abs((currAngle - angle) * stepsPerDegree));
        currAngle = angle;
        ardFac.stepAmount(steps);

    }
}
