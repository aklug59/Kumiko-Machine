package CoreLogic;
import java.util.Timer;
import java.util.TimerTask;

public class ProjectTimer {

    //Variable declarations
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

    //Stop the current timer and store the endTime.
    protected static void stopTimer() {
        guiTimerTask.cancel();
        endTime = System.currentTimeMillis();
    }

    //Calculate the time it took to make the current piece.
    protected static long getPieceTime() {
        return endTime - startTime;
    }

    //Reset the timer to zero and start iteration.
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


    /*Inform the ModelFacade that a timer update has occured (every second). New time is passed back up the
     * chain to GUI where timer textField is changed.
     */
    protected static void GUITimeUpdate() {
        projectTimerLocalModelFacade.updateTime(currTime);
        currTime++;
    }
}
