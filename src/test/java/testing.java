
import View.GUI;



public class testing {
    static TestMethods tester = new TestMethods();
    static MockArduino mockArd = new MockArduino(1,1,1000);
    static GUI GUI = new GUI();




    public static void main(String[] args) throws InterruptedException {

        GUI.createGUI();

        //mockArd.mockStepper();
        //tester.Stepper();

    }
}
