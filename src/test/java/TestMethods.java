import CoreLogic.Actuator;
import CoreLogic.ArduinoFacade;
import CoreLogic.CutterHead;

import static CoreLogic.ArduinoFacade.getArduinoFacade;

public class TestMethods {

    ArduinoFacade ardFac = getArduinoFacade();

    public void Stepper() throws InterruptedException {
        ardFac.openConnection();
        CutterHead testCutter = new CutterHead();
        testCutter.setAngle(0);
        Thread.sleep(3000);
        testCutter.setAngle(90);
    }

    public void Actuator() throws InterruptedException {
        ardFac.openConnection();
        Actuator testActuator = new Actuator();
        testActuator.setPosition(100);
        Thread.sleep(10000);
        testActuator.setPosition(0);



    }


}
