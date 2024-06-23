import CoreLogic.ArduinoFacade;
import CoreLogic.CutterHead;

public class TestMethods {
    public void Stepper() throws InterruptedException {
        ArduinoFacade ardFac = new ArduinoFacade();
        ardFac.openConnection();
        CutterHead testCutter = new CutterHead(ardFac);
        testCutter.setAngle(0);
        Thread.sleep(3000);
        testCutter.setAngle(90);
    }


}
