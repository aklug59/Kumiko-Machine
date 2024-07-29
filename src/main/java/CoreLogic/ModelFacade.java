package CoreLogic;

import Adapter.Adapter;

import static Adapter.Adapter.getAdapter;
import static CoreLogic.Constants.*;

public class ModelFacade {

    //Variable Declarations
    Adapter ModelFascadeLocalAdapter = getAdapter();
    public static final float timeDivisor = 1000f;
    private static ModelFacade modelFacadeInstance;
    private static CutterHead CutterHead = new CutterHead();
    private Actuator Actuator = new Actuator();

    //Singleton pattern constructor and getModeFacadeMethod
    private ModelFacade() {}

    //Singleton pattern
    public static ModelFacade getModelFascade() {
        if (ModelFacade.modelFacadeInstance == null) {
            modelFacadeInstance = new ModelFacade();
        }
        return modelFacadeInstance;
    }
    //Pass a new angle to the CutterHead.
    public void changeAngle(double newAngle) throws InterruptedException { CutterHead.setAngle(newAngle); }

    //Ask the CutterHead to confirm that a new angle is within bounds.
    public static boolean checkAngleBounds(double newAngle) {return CutterHead.checkAngleBounds(newAngle);}

    //Tell the actuator to change its position.
    public void changePosition(int position) throws InterruptedException { Actuator.setPosition(position); }

    // Ask the actuator to confirm that the new position is within bounds.
    public boolean checkPositionBounds(int position, int direction) {return Actuator.checkPositionBounds(position, direction);}

    //Update Piece information. A switch ensures that the appropriate length is updated.
    public void changePieceLength(double length, String value) {
        switch(value) {
            case START:
                Piece.startingLength = length;
                break;
            case CURRENT:
                Piece.currLength = length;
                break;
            case TARGET:
                Piece.targetLength = length;
                break;
        }
    }

    //Star the project timer.
    public void startTimer() {
        ProjectTimer.resetTimer();
    }

    //This method is called on a schedule from project timer. It passes the current time back up the chain to GUI.
    public void updateTime(int currTime) { ModelFascadeLocalAdapter.updateTime(currTime); }

    //Save the piece and reset all pertinent information to allow for a new piece to be created.
    public void savePiece() {
        ProjectTimer.stopTimer();
        float pieceConstructionTime = ProjectTimer.getPieceTime() / timeDivisor;
        ProjectTimer.resetTimer();
        FileWriter.writePiece(Piece.startingLength,pieceConstructionTime);
        Piece.resetLengths();
    }

    //Tell FileReader to get the name of the current project.
    public String getProjectName() { return FileReader.getProjectName(); }

    //Tell the Actuator to calculate and return the length of the piece after a cut has been made.
    public double getCutLength() {return Actuator.getCutLength();}

    }

