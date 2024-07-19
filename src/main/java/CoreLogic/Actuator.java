package CoreLogic;

import static CoreLogic.ArduinoFacade.getArduinoFacade;

public class Actuator {
    public static int position;
    final static char initilizer = 2;
    private final double strokeLength = 2.806;
    private final double inchPerStep = .010064453125;
    static ArduinoInterface ardFac = getArduinoFacade();
    public Actuator() {};
    public void setPosition(int newPosition) throws InterruptedException {

        position = newPosition;
        ardFac.initial(initilizer);
        Thread.sleep(800);
        ardFac.newPosition(position);

    }

    public double getCutLength() { return strokeLength - (inchPerStep * position); }

}
