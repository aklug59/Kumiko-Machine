package examples;

import CoreLogic.ArduinoFacade;

public class CutterHeadExample {

    public static void main(String[] args) throws InterruptedException {

        ArduinoFacade ardFac = new ArduinoFacade();
        ardFac.openConnection();
        ardFac.initial(1);
        Thread.sleep(1000);
        ardFac.stepAmount(675);
        String x = ardFac.read();
        System.out.println(x);


    }
}
