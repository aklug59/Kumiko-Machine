package CoreLogic;

import arduino.Arduino;

import java.util.Scanner;

import static CoreLogic.Constants.CLOSED;

public class ArduinoFacade implements ArduinoInterface {
    public static String currentState = CLOSED;
    public static final int baudRate = 9600;
    private static ArduinoFacade arduinoFascadeInstance;
    Scanner scnr = new Scanner(System.in);
    private final Arduino arduino = new Arduino("COM7", baudRate); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.

    private ArduinoFacade() {};

    public static ArduinoFacade getArduinoFacade() {

        if (ArduinoFacade.arduinoFascadeInstance == null) {
            arduinoFascadeInstance = new ArduinoFacade();
        }
        return arduinoFascadeInstance;
    }

    /**Open a connection to the arduino.*/
    public void openConnection() {
        arduino.openConnection();
    }

    /**Tell the arduino to move either the cutterhead or the actuator. CutterHead = 1, Actuator = 2.*/
    public void initial(int x) {
        String stringX = String.valueOf(x);
        arduino.serialWrite(stringX);
    }

    /**Tell the arduino how many steps to move the CutterHead.*/
    public void stepAmount(int steps) {
        String stringAngle = String.valueOf(steps);
        arduino.serialWrite(stringAngle);
    }

    /**Tell the arduino how far to move the actuator.*/
    public void newPosition(int position) {
        String positionString = String.valueOf(position);
        arduino.serialWrite(positionString);
    }

    /**Testing method, not used outside of initial cutterhead testing via CutterHeadExample class.*/
    public String read() {
        return arduino.serialRead();
    }

}
