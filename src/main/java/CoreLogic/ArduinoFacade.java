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

    public void openConnection() {
        arduino.openConnection();
    }

    public void closeConnection() {
        scnr.close();
        arduino.closeConnection();
    }

    public void stepAmount(int steps) {
        String stringAngle = String.valueOf(steps);
        arduino.serialWrite(stringAngle);
    }

    public void initial(int x) {
        String stringX = String.valueOf(x);
        arduino.serialWrite(stringX);
    }

    public void newPosition(int position) {
        String positionString = String.valueOf(position);
        arduino.serialWrite(positionString);
    }

    public String read() {
        return arduino.serialRead();
    }

}
