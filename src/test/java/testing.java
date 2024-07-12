import CoreLogic.ProjectTimer;
import View.GUI;

import java.io.FileNotFoundException;

import static View.GUI.populateGUI;


public class testing {
    static TestMethods tester = new TestMethods();
    static MockArduino mockArd = new MockArduino(2,1,1000, 40);
    static ProjectTimer testTimer = new ProjectTimer();
    static GUI thisGUI = GUI.getGUI();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //FileWriter.writePiece(1,2);
        //Thread.sleep(1000);
        //FileWriter.writePiece(1,4);

        //FileReader.getProjectName();
        populateGUI();

        //GUI.populateGUI();
        //mockArd.mockActuator();

        //mockArd.mockStepper();
        //tester.Stepper();
        //tester.Actuator();

    }
}
