package View;

import Adapter.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import static Adapter.Adapter.getAdapter;
import static CoreLogic.Constants.*;


public class GUI extends ListeningAdapter {
    // Variable declarations
    protected static GUI guiInstance = new GUI();
    protected static Adapter guiLocalAdapter = getAdapter();
    protected static JFrame frame = new JFrame();
    protected static JProgressBar progressBar = new JProgressBar();
    protected static JTextField angleTextField = new JTextField(1);
    protected static JTextField positionTextField = new JTextField(1);
    protected static JTextField startingLengthTextField = new JTextField();
    protected static JTextField currLengthTextField = new JTextField("");
    protected static JTextField targetLengthTextField = new JTextField("");
    protected static JTextField errorTextField = new JTextField("No errors");
    protected static JTextField pieceTimeTextField = new JTextField("");
    protected static JButton anglePlusButton = new JButton("+ .5°");
    protected static JButton angleMinusButton = new JButton("- .5°");
    protected static JButton positionPlusButton = new JButton("+");
    protected static JButton positionMinusButton = new JButton("-");
    protected static JLabel angleLabel = new JLabel("Current Angle");
    protected static JLabel positionLabel = new JLabel("Current Position");
    protected static JLabel startingLengthLabel = new JLabel("Starting Length");
    protected static JLabel currentLengthLabel = new JLabel("Current Length");
    protected static JLabel targetLengthLabel = new JLabel("Target Length");
    protected static JLabel timeLabel = new JLabel("Elapsed Time:");
    protected static JLabel projectNameLabel = new JLabel("");
    protected static JLabel progressBarLabel = new JLabel("Piece Progress");
    protected static Font boldFont = new Font("BOLD",Font.BOLD, anglePlusButton.getFont().getSize());
    protected static double currGUIAngle = 90;
    protected static int currGUIPosition = 0;
    double startingLength;
    double currLength;
    double targetLength;
    boolean firstCut = false;

    //Singleton constructor and getGUI method
    private GUI() {};

    /** Allow outside classes to access and instance of the single GUI object */
    public static GUI getGUI() {
        if (GUI.guiInstance == null) {
            guiInstance = new GUI();
        }
        return guiInstance;
    }
    /**Tell the various factories associated with GUI to make their components.*/
    public static void populateGUI() {
        TextFieldFactory.makeTextFields();
        ButtonFactory.makeButtons();
        LabelFactory.makeLabels();
        FrameFactory.makeFrame();
        ProgressBarFactory.makeProgressBars();
    }

    /** Move the cutterhead +/- .5 degree based on which GUI button was pressed.*/
    public void nudgeAngle(String direction) throws InterruptedException {
        currGUIAngle = Double.parseDouble(angleTextField.getText());

        switch(direction) {

            case UP:
                if (guiLocalAdapter.checkAngleBounds(currGUIAngle + angleNudge)) {
                    guiLocalAdapter.angleUpdate(currGUIAngle + angleNudge);
                    angleTextField.setText(String.valueOf(currGUIAngle + angleNudge));
                } else { errorWarning(BAD_ANGLE); }
                break;
            case DOWN:
                if (guiLocalAdapter.checkAngleBounds(currGUIAngle - angleNudge)) {
                    guiLocalAdapter.angleUpdate(currGUIAngle - angleNudge);
                    angleTextField.setText(String.valueOf(currGUIAngle - angleNudge));
                } else { errorWarning(BAD_ANGLE); }
                break;
        }
    }

    /**Moves the actuator +/- 5 based on GUI button press.*/
    public void nudgePosition (int direction) throws InterruptedException {
        currGUIPosition = Integer.parseInt(positionTextField.getText());
        int nextPosition;
        if (direction == FORWARD) {
            nextPosition = currGUIPosition + positionNudge;
        } else {
            nextPosition = currGUIPosition - positionNudge;
        }
        if (guiLocalAdapter.checkPositionBounds(nextPosition, direction)) {
            errorWarning(BAD_POSITION);
        } else {
            if (direction == FORWARD) {
                currGUIPosition = currGUIPosition + positionNudge;
                positionTextField.setText(String.valueOf(currGUIPosition));
                guiLocalAdapter.updatePosition(currGUIPosition);

            } else {
                currGUIPosition = currGUIPosition - positionNudge;
                positionTextField.setText(String.valueOf(currGUIPosition));
                guiLocalAdapter.updatePosition(currGUIPosition);
            }
        }
    }

    /**Updates the display to iterate the pieceTimeTextField. This method is scheduled on 1 second calls from ProjectTimer.*/
    public void updateGUITime(int currTime) {
        String currTimeString;
        currTimeString = String.valueOf(currTime);
        pieceTimeTextField.setText(currTimeString);
    }

    /**Simple reset method for resetting the GUI piece text fields to "0".*/
    public void resetGUIPieceValues() {
        startingLengthTextField.setText(ZERO);
        currLengthTextField.setText(ZERO);
        targetLengthTextField.setText(ZERO);
        pieceTimeTextField.setText(ZERO);
    }

    /**Listener for action events on the various GUI components. The object on which the event occurred is determined and
    * a switch delegates to the appropriate method calls.
    */
    public void actionPerformed(ActionEvent e) {
        JComponent currObject = (JComponent) e.getSource();
        String currName = String.valueOf(currObject.getName());

        switch(currName) {
            case STARTING_LENGTH_TEXTFIELD -> {
                startingLength = Double.parseDouble(startingLengthTextField.getText());
                frame.requestFocusInWindow();
                guiLocalAdapter.updatePiece(startingLength, START);
            }

            case TARGET_LENGTH_TEXTFIELD -> {
                targetLength = Double.parseDouble(targetLengthTextField.getText());
                frame.requestFocusInWindow();
                guiLocalAdapter.updatePiece(targetLength, TARGET);
            }

            case CURR_LENGTH_TEXTFIELD -> {
                errorWarning(currName);
                frame.requestFocusInWindow();
            }
            case ANGLE_PLUS_BUTTON -> {
                try {
                    nudgeAngle(UP);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case ANGLE_MINUS_BUTTON -> {
                try {
                    nudgeAngle(DOWN);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case POSITION_MINUS_BUTTON -> {
                try {
                    nudgePosition(BACK);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case POSITION_PLUS_BUTTON -> {
                try {
                    nudgePosition(FORWARD);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case POSITION_TEXTFIELD -> {
                currGUIPosition = Integer.parseInt(positionTextField.getText());
                if (!guiLocalAdapter.checkPositionBounds(currGUIPosition,FORWARD)) {
                    try {
                        guiLocalAdapter.updatePosition(currGUIPosition);
                        frame.requestFocusInWindow();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    errorWarning(BAD_POSITION);
                }
            }
            case ANGLE_TEXTFIELD -> {
                currGUIAngle = Double.parseDouble(angleTextField.getText());
                if (!guiLocalAdapter.checkAngleBounds(currGUIAngle)) {
                    errorWarning(BAD_ANGLE);
                    frame.requestFocusInWindow();
                } else {
                    angleTextField.getText();
                    frame.requestFocusInWindow();
                    try {
                        guiLocalAdapter.angleUpdate(currGUIAngle);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    /**After a cut has been made, the progress bar is updated. This method determines and returns that value.*/
    public int getNewProgressBarValue() {
        double newProgressVal = Math.round(((startingLength - currLength) / (startingLength - targetLength)) * percentModifier);
        return (int) newProgressVal;
    }

    /** KeyListener for hotkey events. HOTKEYS - > "c" (cut made) and "n" (new piece)*/
    public void keyPressed(KeyEvent e) {

        int currKey = e.getKeyCode();

        //Inform the model a cut has been made and update the piece object and GUI accordingly.
        if (currKey == KeyEvent.VK_C) {
            if (!firstCut) {
                guiLocalAdapter.startTimer();
                firstCut = true;
            }
            currLength = guiLocalAdapter.getCutLength();
            currLengthTextField.setText(String.valueOf(currLength));
            guiLocalAdapter.updatePiece(currLength, CURRENT);
            ProgressBarFactory.updateBar(getNewProgressBarValue());

        }

        //Save the current piece and make a new one.
        if (currKey == KeyEvent.VK_N) {
            if (!firstCut) {
                errorWarning(NO_PIECE);
            } else {
                guiLocalAdapter.savePiece();
                ProgressBarFactory.updateBar(progressBarZero);
                resetGUIPieceValues();
            }
        }
    }
    /**Error handling method. Switch determines appropriate action based on string expression passed from caller.*/
    public void errorWarning(String warning) {
        //String switch for error handling
        switch(warning) {
            case CURR_LENGTH_TEXTFIELD -> {
                errorTextField.setBackground(Color.RED);
                errorTextField.setText("Do not touch the current length!");
                resetErrorTextField();
            }
            case NO_PIECE -> {
                errorTextField.setBackground(Color.red);
                errorTextField.setText("There is no piece to save!");
                resetErrorTextField();
            }
            case BAD_POSITION -> {
                errorTextField.setBackground(Color.RED);
                errorTextField.setText("Position must be between 0 - 255!");
                resetErrorTextField();
            }
            case BAD_ANGLE -> {
                errorTextField.setBackground(Color.RED);
                errorTextField.setText("Angle must be between 0 and 90!");
                resetErrorTextField();
            }
            default -> {
                errorTextField.setBackground(Color.RED);
                errorTextField.setText("An error occurred!");
                resetErrorTextField();
            }
        }
    }
    /**Reset the error textfield 5 seconds after an error has been thrown.*/
    public void resetErrorTextField() {
        TimerTask task = new TimerTask() {
            public void run() {
                errorTextField.setText("No errors");
                errorTextField.setBackground(Color.GREEN);
            }
        };

        java.util.Timer currTimer = new Timer();
        currTimer.schedule(task, errorTimeout);
    }
}
