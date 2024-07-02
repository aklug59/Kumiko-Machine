package CoreLogic;
import java.util.Timer;
import java.util.TimerTask;

public class ProjectTimer {

    protected static long startTime;
    protected long endTime;

    private static ModelFascade projectTimerLocalModelFacade = ModelFascade.getModelFascade();

    private static Timer projectTimer = new Timer();

    public static TimerTask guiTimeUpdate = new TimerTask() {
        @Override
        public void run() {
            ProjectTimer.GUITimeUpdate();
        }
    };

    public ProjectTimer() {}

    protected static void startTimer() {
        System.out.println("We got here!");
        startTime = System.currentTimeMillis();
        projectTimer.schedule(guiTimeUpdate, 0,1000);
        //projectTimer.schedule(guiTimeUpdate,startTime,1000);
    }

    protected void stopTimer() {
        endTime = System.currentTimeMillis();
    }

    protected long getPieceTime() {
        return endTime - startTime;
    }
    
    protected static void GUITimeUpdate() {
        projectTimerLocalModelFacade.updateTime();
        System.out.println("Tick!");


    }





}
