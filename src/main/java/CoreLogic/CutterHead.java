package CoreLogic;

import CoreLogic.ArduinoFacade;

/*
* 800ms delay seems to me minimum functional threshold where the serial on the arduino isn't skipping reads
*
*
* */
public class CutterHead {
    static double currAngle = 90;
    static int motorDirection = 0;
//.1125
    static double steps = 0;

    final static double stepsPerDegree = 8.8888;
    static ArduinoFacade ardFac;

    public CutterHead(ArduinoFacade ardFac) {
        this.ardFac = ardFac;
    }

    public static void setAngle(double angle) throws InterruptedException {
        System.out.println("The starting currAngle is " + currAngle);

        if (angle < currAngle) {
            motorDirection = -1;
        }
        else if (angle > currAngle){
            motorDirection = 1;
        } else if (angle == currAngle) {
            System.out.println("This is the same angle!");
        }
        ardFac.initial(1);
        Thread.sleep(800);
        ardFac.initial(motorDirection);
        Thread.sleep(800);
        steps = Math.abs((currAngle - angle) * stepsPerDegree);
        int intSteps = (int)Math.round(steps);
        currAngle = angle;
        System.out.println("The currAngle is " + currAngle);
        ardFac.cutter(intSteps);
        String x = ardFac.read();
        System.out.println(x);

    }



}
