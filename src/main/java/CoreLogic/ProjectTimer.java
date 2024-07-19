package CoreLogic;
import java.util.Timer;
import java.util.TimerTask;

public class ProjectTimer {

    protected static long startTime;
    protected static long endTime;
    protected static int currTime = 0;

    private static final ModelFacade projectTimerLocalModelFacade = ModelFacade.getModelFascade();

    public static TimerTask guiTimerTask = new TimerTask() {
        @Override
        public void run() {
            ProjectTimer.GUITimeUpdate();
        }
    };

    public ProjectTimer() {}
    protected static void stopTimer() {
        guiTimerTask.cancel();
        endTime = System.currentTimeMillis();
    }

    protected static long getPieceTime() {
        return endTime - startTime;
    }

    protected static void resetTimer() {
        startTime = System.currentTimeMillis();
        Timer projectTimer = new Timer();
        currTime = 0;
        guiTimerTask = new TimerTask() {
            @Override
            public void run() {
                ProjectTimer.GUITimeUpdate();
            }
        };
        projectTimer.schedule(guiTimerTask, 0,1000);
    }

    
    protected static void GUITimeUpdate() {
        projectTimerLocalModelFacade.updateTime(currTime);
        currTime++;
    }
}
