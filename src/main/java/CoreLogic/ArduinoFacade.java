package CoreLogic;

import arduino.Arduino;

import java.util.*;

public class ArduinoFacade {
    Scanner ob = new Scanner(System.in);
    private final Arduino arduino = new Arduino("COM7", 9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.

    public void openConnection() {
        arduino.openConnection();
    }

    public void closeConnection() {
        ob.close();
        arduino.closeConnection();
    }

    public void cutter(int steps) {
        String stringAngle = String.valueOf(steps);
        arduino.serialWrite(stringAngle);
    }

    public void initial(int x) {
        String stringX = String.valueOf(x);
        arduino.serialWrite(stringX);
    }

    public String read() {
        return arduino.serialRead();
    }


}
