
import CoreLogic.ArduinoFacade;
import CoreLogic.ArduinoInterface;
import CoreLogic.CutterHead;
import View.GUI;

import static View.GUI.populateGUI;


public class testing {
    static TestMethods tester = new TestMethods();
    static MockArduino mockArd = new MockArduino(1,1,1000);

    static GUI thisGUI = GUI.getGUI();



    public static void main(String[] args) throws InterruptedException {

        populateGUI();

        //mockArd.mockStepper();
        //tester.Stepper();

    }
}
