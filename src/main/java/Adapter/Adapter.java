package Adapter;

import CoreLogic.ModelFacade;
import View.GUI;

import static CoreLogic.ModelFacade.getModelFascade;
import static View.GUI.getGUI;


public class Adapter {

    private static Adapter adapterInstance;
    private static final GUI adapterLocalGUI = getGUI();
    private static final ModelFacade adapterLocalModelFascade = getModelFascade();

    // Singleton pattern adapter constructor and getAdapter method
    private Adapter() {}

    public static Adapter getAdapter() {
        if (Adapter.adapterInstance == null) {
            adapterInstance = new Adapter();
        }
        return adapterInstance;

    }
    public void angleUpdate(double newAngle) throws InterruptedException { adapterLocalModelFascade.changeAngle(newAngle); }
    public void updatePosition(int newPosition) throws InterruptedException { adapterLocalModelFascade.changePosition(newPosition); }
    public void updatePiece(double length, String value) { adapterLocalModelFascade.changePieceLength(length, value); }
    public void updateTime() { adapterLocalGUI.updateTime(); }
    public void savePiece()  { adapterLocalModelFascade.savePiece(); }
    public void startTimer() {
        adapterLocalModelFascade.startTimer();
    }
    public String getProjectName() {
        return adapterLocalModelFascade.getProjectName();
    }

}
