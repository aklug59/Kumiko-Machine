package CoreLogic;

import static CoreLogic.ArduinoFacade.currentState;
import static CoreLogic.ArduinoFacade.getArduinoFacade;
import static View.GUI.populateGUI;

public class Actuator {
    public static int position;
    final static char initilizer = 2;
    static ArduinoInterface ardFac = getArduinoFacade();
    public Actuator() {};
    public void setPosition(int newPosition) throws InterruptedException {

        //If a connection between the computer and the Arduino has not yet been made, make it.
        /*if (currentState.equals("Closed")) {
            ardFac.openConnection();
            currentState = "Open";
        }*/
        position = newPosition;
        ardFac.initial(initilizer);


        Thread.sleep(800);
        ardFac.newPosition(position);

    }
}
