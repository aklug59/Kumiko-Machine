package CoreLogic;

import static CoreLogic.ArduinoFacade.currentState;

/*
* 800ms delay seems to me minimum functional threshold where the serial on the arduino isn't skipping reads
*
*/
public class CutterHead {
    private static double currAngle = 90;
    private static int motorDirection = 0;
    private static int steps = 0;
    final static char initilizer = 1;
    final static double stepsPerDegree = 8.8888;
    static ArduinoInterface ardFac = new ArduinoFacade();


    public CutterHead() {}

    public static void setAngle(double angle) throws InterruptedException {
        //If a connection between the computer and the Arduino has not yet been made, make it.
        if (currentState.equals("Closed")) {
            ardFac.openConnection();
            currentState = "Open";
        }

        /*Send the arduino an integer indicating StepperMotor vs.
        actuator control. 1 = StepperMotor, 2 = actuator. */
        ardFac.initial(initilizer);

        // Set motor direction based on current angle.
        if (angle < currAngle) {
            motorDirection = -1;
        }
        else if (angle > currAngle){
            motorDirection = 1;
        } else if (angle == currAngle) {
            //Warn the user that the same angle is being used. Implement in GUI once written.
            System.out.println("This is the same angle!");
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
