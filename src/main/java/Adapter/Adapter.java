package Adapter;

import CoreLogic.ModelFacade;
import View.GUI;

import static CoreLogic.ModelFacade.getModelFascade;
import static View.GUI.getGUI;


public class Adapter {

    //Variable declarations.
    private static Adapter adapterInstance;
    private static final GUI adapterLocalGUI = getGUI();
    private static final ModelFacade adapterLocalModelFacade = getModelFascade();

    // Singleton pattern adapter constructor and getAdapter method
    private Adapter() {}

    public static Adapter getAdapter() {
        if (Adapter.adapterInstance == null) {
            adapterInstance = new Adapter();
        }
        return adapterInstance;
    }

    //All adapter methods pass requests from GUI to ModelFacade for logic handling.
    public void angleUpdate(double newAngle) throws InterruptedException { adapterLocalModelFacade.changeAngle(newAngle); }
    public static boolean checkAngleBounds(double newAngle) {return adapterLocalModelFacade.checkAngleBounds(newAngle);}
    public void updatePosition(int newPosition) throws InterruptedException { adapterLocalModelFacade.changePosition(newPosition); }
    public static boolean checkPositionBounds(int position, int direction) {return adapterLocalModelFacade.checkPositionBounds(position,direction);}
    public void updatePiece(double length, String value) { adapterLocalModelFacade.changePieceLength(length, value); }
    public void updateTime(int currTime) { adapterLocalGUI.updateGUITime(currTime); }
    public void savePiece()  { adapterLocalModelFacade.savePiece(); }
    public void startTimer() {
        adapterLocalModelFacade.startTimer();
    }
    public String getProjectName() {
        return adapterLocalModelFacade.getProjectName();
    }
    public double getCutLength() { return adapterLocalModelFacade.getCutLength(); }

}
