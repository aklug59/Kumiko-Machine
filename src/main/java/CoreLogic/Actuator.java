package CoreLogic;

import static CoreLogic.ArduinoFacade.getArduinoFacade;

public class Actuator {
    public static int position;
    final static char initilizer = 2;
    static ArduinoInterface ardFac = getArduinoFacade();
    public Actuator() {};
    public void setPosition(int newPosition) throws InterruptedException {

        position = newPosition;
        ardFac.initial(initilizer);
        Thread.sleep(800);
        ardFac.newPosition(position);

    }
}
