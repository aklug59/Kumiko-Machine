import CoreLogic.FileWriter;
import CoreLogic.ProjectTimer;
import View.GUI;


public class testing {
    static TestMethods tester = new TestMethods();
    static MockArduino mockArd = new MockArduino(2,1,1000, 40);
    static ProjectTimer testTimer = new ProjectTimer();
    static GUI thisGUI = GUI.getGUI();

    public static void main(String[] args) throws InterruptedException {
        FileWriter.writePiece(1);
        //FileWriter.writePiece(2);
        //FileReader.getProjectName();
        //populateGUI();

        //GUI.populateGUI();
        //mockArd.mockActuator();

        //mockArd.mockStepper();
        //tester.Stepper();
        //tester.Actuator();

    }
}
