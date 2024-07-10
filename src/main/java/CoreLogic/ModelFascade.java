package CoreLogic;

import Adapter.Adapter;

import java.io.FileNotFoundException;

import static Adapter.Adapter.getAdapter;

public class ModelFascade {

    Adapter ModelFascadeLocalAdapter = getAdapter();
    private static ModelFascade modelFascadeInstance;
    private ModelFascade() {}
    private CutterHead CutterHead = new CutterHead();

    private Actuator Actuator = new Actuator();

    public static ModelFascade getModelFascade() {
        if (ModelFascade.modelFascadeInstance == null) {
            modelFascadeInstance = new ModelFascade();
        }
        return modelFascadeInstance;
    }
    public void changeAngle(double newAngle) throws InterruptedException { CutterHead.setAngle(newAngle); }
    public void changePosition(int position) throws InterruptedException { Actuator.setPosition(position); }

    public void changePieceLength(double length, String value) {
        switch(value) {
            case "start":
                Piece.startingLength = length;
                break;
            case "current":
                Piece.currLength = length;
                break;
            case "target":
                Piece.targetLength = length;
                break;
        }
    }

    public void startTimer() {
        ProjectTimer.resetTimer();
    }
    public void updateTime() { ModelFascadeLocalAdapter.updateTime(); }

    public void savePiece() throws FileNotFoundException {
            ProjectTimer.stopTimer();
            float pieceConstructionTime = ProjectTimer.getPieceTime() / 1000f;
            ProjectTimer.resetTimer();
            FileWriter.writePiece(Piece.startingLength,pieceConstructionTime);
            Piece.resetLengths();
    }
    public String getProjectName() { return FileReader.getProjectName(); }

    }
