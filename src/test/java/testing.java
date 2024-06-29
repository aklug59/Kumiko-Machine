
import CoreLogic.Actuator;
import CoreLogic.ArduinoFacade;
import CoreLogic.ArduinoInterface;
import CoreLogic.CutterHead;
import View.GUI;

import static View.GUI.populateGUI;


public class testing {
    static TestMethods tester = new TestMethods();
    static MockArduino mockArd = new MockArduino(2,1,1000, 40);

    static GUI thisGUI = GUI.getGUI();



    public static void main(String[] args) throws InterruptedException {
        /*populateGUI();
        Actuator testAc = new Actuator();
        testAc.setPosition(50);
        Thread.sleep(10000);
        testAc.setPosition(100);
        Thread.sleep(10000);
        testAc.setPosition(50);
        Thread.sleep(10000);
        testAc.setPosition(0);*/
        populateGUI();
        //mockArd.mockActuator();

        //mockArd.mockStepper();
        //tester.Stepper();
        //tester.Actuator();

    }
}
