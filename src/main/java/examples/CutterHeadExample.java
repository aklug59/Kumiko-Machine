package examples;

import CoreLogic.ArduinoFacade;

import static CoreLogic.ArduinoFacade.getArduinoFacade;
//TESTING METHOD TO CONFIRM THE MOTOR CAN BE CONTROLLED FROM THE COMPUTER.
public class CutterHeadExample {

    public static void main(String[] args) throws InterruptedException {

        ArduinoFacade ardFac = getArduinoFacade();
        ardFac.openConnection();
        ardFac.initial(1);
        Thread.sleep(1000);
        ardFac.stepAmount(675);
        String x = ardFac.read();
        System.out.println(x);


    }
}
