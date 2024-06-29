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


    }
