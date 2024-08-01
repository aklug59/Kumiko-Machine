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
    /** Allow outside classes to access and instance of the single Adapter object */
    public static Adapter getAdapter() {
        if (Adapter.adapterInstance == null) {
            adapterInstance = new Adapter();
        }
        return adapterInstance;
    }

    //All adapter methods pass requests from GUI to ModelFacade for logic handling.

    /** Passes the new angle from the GUI to the ModelFacade for handling */
    public void angleUpdate(double newAngle) throws InterruptedException { adapterLocalModelFacade.changeAngle(newAngle); }
    /** Asks the model Facade to confirm that the new angle is within limits */
    public static boolean checkAngleBounds(double newAngle) {return adapterLocalModelFacade.checkAngleBounds(newAngle);}
    /** Pass the new position from the GUI to the ModelFacade for handling*/
    public void updatePosition(int newPosition) throws InterruptedException { adapterLocalModelFacade.changePosition(newPosition); }
    /** Asks the ModelFacade to confrim that the new position is within limits*/
    public static boolean checkPositionBounds(int position, int direction) {return adapterLocalModelFacade.checkPositionBounds(position,direction);}
    /** Update the piece information after a cut has been made*/
    public void updatePiece(double length, String value) { adapterLocalModelFacade.changePieceLength(length, value); }
    /** Ask the GUI to update the time with the new currTime from ProjectTimer*/
    public void updateTime(int currTime) { adapterLocalGUI.updateGUITime(currTime); }
    /** Ask the model facade to save the piece*/
    public void savePiece()  { adapterLocalModelFacade.savePiece(); }
    /** Ask the model facade to start the timer*/
    public void startTimer() {
        adapterLocalModelFacade.startTimer();
    }
    /** Ask the ModelFacade for the project name*/
    public String getProjectName() {
        return adapterLocalModelFacade.getProjectName();
    }
    /** Ask the ModelFacade for the cut length after a cut has been made*/
    public double getCutLength() { return adapterLocalModelFacade.getCutLength(); }

}
