package CoreLogic;

import Adapter.Adapter;

import static Adapter.Adapter.getAdapter;
import static CoreLogic.Constants.*;

public class ModelFacade {

    Adapter ModelFascadeLocalAdapter = getAdapter();
    public static final float timeDivisor = 1000f;
    private static ModelFacade modelFacadeInstance;
    private static CutterHead CutterHead = new CutterHead();
    private Actuator Actuator = new Actuator();

    //Singleton pattern constructor and getModeFacadeMethod
    private ModelFacade() {}

    //Singleton patter
    public static ModelFacade getModelFascade() {
        if (ModelFacade.modelFacadeInstance == null) {
            modelFacadeInstance = new ModelFacade();
        }
        return modelFacadeInstance;
    }
    public void changeAngle(double newAngle) throws InterruptedException { CutterHead.setAngle(newAngle); }

    public static boolean checkAngleBounds(double newAngle) {return CutterHead.checkAngleBounds(newAngle);}
    public void changePosition(int position) throws InterruptedException { Actuator.setPosition(position); }

    public boolean checkPositionBounds(int position, int direction) {return Actuator.checkPositionBounds(position, direction);}

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

    public void startTimer() {
        ProjectTimer.resetTimer();
    }
    public void updateTime(int currTime) { ModelFascadeLocalAdapter.updateTime(currTime); }

    public void savePiece() {
        ProjectTimer.stopTimer();
        float pieceConstructionTime = ProjectTimer.getPieceTime() / timeDivisor;
        ProjectTimer.resetTimer();
        FileWriter.writePiece(Piece.startingLength,pieceConstructionTime);
        Piece.resetLengths();
    }
    public String getProjectName() { return FileReader.getProjectName(); }
    public double getCutLength() {return Actuator.getCutLength();}

    }

