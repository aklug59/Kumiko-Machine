package CoreLogic;

import Adapter.Adapter;

import static Adapter.Adapter.getAdapter;
import static CoreLogic.Constants.*;

public class ModelFacade {

    Adapter ModelFascadeLocalAdapter = getAdapter();
    private static ModelFacade modelFascadeInstance;
    private ModelFacade() {}
    private CutterHead CutterHead = new CutterHead();

    private Actuator Actuator = new Actuator();

    public static ModelFacade getModelFascade() {
        if (ModelFacade.modelFascadeInstance == null) {
            modelFascadeInstance = new ModelFacade();
        }
        return modelFascadeInstance;
    }
    public void changeAngle(double newAngle) throws InterruptedException { CutterHead.setAngle(newAngle); }
    public void changePosition(int position) throws InterruptedException { Actuator.setPosition(position); }

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
            float pieceConstructionTime = ProjectTimer.getPieceTime() / 1000f;
            ProjectTimer.resetTimer();
            FileWriter.writePiece(Piece.startingLength,pieceConstructionTime);
            Piece.resetLengths();
    }
    public String getProjectName() { return FileReader.getProjectName(); }

    }
