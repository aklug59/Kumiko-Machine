package Adapter;
import CoreLogic.ModelFascade;
import View.GUI;

import static CoreLogic.ModelFascade.getModelFascade;
import static View.GUI.getGUI;


public class Adapter {

    private static Adapter adapterInstance;
    private static GUI adapterLocalGUI = getGUI();
    private static ModelFascade adapterLocalModelFascade = getModelFascade();


    private Adapter() {}

    public static Adapter getAdapter() {
        if (Adapter.adapterInstance == null) {
            adapterInstance = new Adapter();
        }
        return adapterInstance;

    }

    public void angleUpdate(double newAngle) throws InterruptedException {
        adapterLocalModelFascade.changeAngle(newAngle);
    }

    public void positionUpdate(int newPosition) throws InterruptedException {
        adapterLocalModelFascade.changePosition(newPosition);
    }

}
