package CoreLogic;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ArduinoFacade ardFac = new ArduinoFacade();
        ardFac.openConnection();

        CutterHead testCutter = new CutterHead(ardFac);
        testCutter.setAngle(0);
        Thread.sleep(3000);
        testCutter.setAngle(90);


    }
}
