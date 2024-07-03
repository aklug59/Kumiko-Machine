package CoreLogic;

import Adapter.Adapter;
import static Adapter.Adapter.getAdapter;
import static CoreLogic.CutterHead.setAngle;

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

    public void changeAngle(double newAngle) throws InterruptedException {
        CutterHead.setAngle(newAngle);
    }

    public void changePosition(int position) throws InterruptedException {
        Actuator.setPosition(position);
    }

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


    public void updateTime() {
        ModelFascadeLocalAdapter.updateTime();

    }

    public void savePiece() {
            ProjectTimer.stopTimer();
            float pieceConstructionTime = ProjectTimer.getPieceTime() / 1000f;
            System.out.println("The time to make the piece was " + pieceConstructionTime);
            ProjectTimer.resetTimer();
            System.out.println("The starting length was " + Piece.startingLength);
            System.out.println("The current length is " + Piece.currLength);
            System.out.println("The target length was " + Piece.targetLength);
            Piece.resetLengths();

            //Add logic to save above piece information to xlsx file

    }


    }
