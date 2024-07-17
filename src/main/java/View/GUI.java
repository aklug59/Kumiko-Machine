package View;

import Adapter.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import static Adapter.Adapter.getAdapter;
import static CoreLogic.Constants.*;


public class GUI implements ActionListener, KeyListener {
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
    protected static double currAngle = 90;
    protected static int currPosition = 0;
    double startingLength;
    double currLength;
    double targetLength;
    int currTime = 0;
    boolean firstCut = false;
    private final double strokeLength = 2.806;
    private final double inchPerStep = .010064453125;

    private GUI() {};

    public static GUI getGUI() {
        if (GUI.guiInstance == null) {
            guiInstance = new GUI();
        }
        return guiInstance;
    }

    public static void populateGUI() {
        TextFieldFactory.makeTextFields();
        ButtonFactory.makeButtons();
        LabelFactory.makeLabels();
        FrameFactory.makeFrame();
        ProgressBarFactory.makeProgressBars();
    }

    public void nudgeAngle(String direction) throws InterruptedException {
        currAngle = Double.parseDouble(angleTextField.getText());

        switch(direction) {

            case UP:
                if (currAngle <= 89.50 ) {
                    guiLocalAdapter.angleUpdate(currAngle + .5 );
                    angleTextField.setText(String.valueOf(currAngle + .5));
                } else { errorWarning(BAD_ANGLE); }
                break;
            case DOWN:
                if (currAngle >= .50 ) {
                    guiLocalAdapter.angleUpdate(currAngle - .5 );
                    angleTextField.setText(String.valueOf(currAngle - .5));
                } else { errorWarning(BAD_ANGLE); }
                break;
        }
    }

    public void nudgePosition (int direction) throws InterruptedException {
        currPosition = Integer.parseInt(positionTextField.getText());
        if (currPosition >= 251 && direction > 0 || currPosition <= 4 && direction < 0) {
            errorWarning(BAD_POSITION);

        } else {
            if (direction > 0) {
                currPosition = currPosition + 5;
                positionTextField.setText(String.valueOf(currPosition));
                guiLocalAdapter.updatePosition(currPosition);

            } else {
                currPosition = currPosition - 5;
                positionTextField.setText(String.valueOf(currPosition));
                guiLocalAdapter.updatePosition(currPosition);
            }
        }
    }
    public double getCutLength() { return strokeLength - (inchPerStep * currPosition); }

    public void updateTime() {
        String currTimeString;
        currTime++;
        currTimeString = String.valueOf(currTime);
        pieceTimeTextField.setText(currTimeString);
    }

    public void resetPieceValues() {
        startingLengthTextField.setText(ZERO);
        currLengthTextField.setText(ZERO);
        targetLengthTextField.setText(ZERO);
        pieceTimeTextField.setText(ZERO);
        currTime = 0;
    }

@Override
    public void actionPerformed(ActionEvent e) {
        JComponent currObject = (JComponent) e.getSource();
        String currName = String.valueOf(currObject.getName());

        switch(currName) {
            case STARTING_LENGTH_TEXTFIELD:
                startingLength = Double.parseDouble(startingLengthTextField.getText());
                frame.requestFocusInWindow();
                guiLocalAdapter.updatePiece(startingLength, START);
                break;
            case TARGET_LENGTH_TEXTFIELD:
                targetLength = Double.parseDouble(targetLengthTextField.getText());
                frame.requestFocusInWindow();
                guiLocalAdapter.updatePiece(targetLength, TARGET);
                break;
            case CURR_LENGTH_TEXTFIELD:
                errorWarning(currName);
                frame.requestFocusInWindow();
                break;
            case ANGLE_PLUS_BUTTON:
                try {
                    nudgeAngle(UP);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case ANGLE_MINUS_BUTTON:
                try {
                    nudgeAngle(DOWN);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case POSITION_MINUS_BUTTON:
                try {
                    nudgePosition(-1);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case POSITION_PLUS_BUTTON:
                try {
                    nudgePosition(1);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case POSITION_TEXTFIELD:
                currPosition = Integer.parseInt(positionTextField.getText());
                if (currPosition >= 0 && currPosition <= 255) {
                    try {
                        guiLocalAdapter.updatePosition(currPosition);
                        frame.requestFocusInWindow();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    errorWarning(BAD_POSITION);
                }
                    break;
            case ANGLE_TEXTFIELD:
                currAngle = Double.parseDouble(angleTextField.getText());
                if (currAngle > 90 || currAngle < 0) {
                    errorWarning(BAD_ANGLE);
                    frame.requestFocusInWindow();
                } else {
                    angleTextField.getText();
                    frame.requestFocusInWindow();
                    try {
                        guiLocalAdapter.angleUpdate(currAngle);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        }
    }

    public int getNewProgressBarValue() {
        double newProgressVal = Math.round(((startingLength - currLength) / (startingLength - targetLength)) * 100);
        return Integer.valueOf((int) newProgressVal);
    }
@Override
public void keyPressed(KeyEvent e) {

    int currKey = e.getKeyCode();

    //Inform the model a cut has been made and update the piece object and GUI accordingly.
    if (currKey == KeyEvent.VK_C) {
        if (!firstCut) {
            guiLocalAdapter.startTimer();
            firstCut = true;
        }
        currLength = getCutLength();
        currLengthTextField.setText(String.valueOf(currLength));
        guiLocalAdapter.updatePiece(getCutLength(), CURRENT);
        ProgressBarFactory.updateBar(getNewProgressBarValue());

    }

    //Save the current piece and make a new one.
    if (currKey == KeyEvent.VK_N) {
        if (!firstCut) {
            errorWarning(NO_PIECE);
        } else {
            guiLocalAdapter.savePiece();
            ProgressBarFactory.updateBar(0);
            resetPieceValues();
        }
    }
}


public void errorWarning(String warning) {

    switch(warning) {

        case CURR_LENGTH_TEXTFIELD:
            errorTextField.setBackground(Color.RED);
            errorTextField.setText("Do not touch the current length!");
            resetErrorTextField();
            break;

        case NO_PIECE:
            errorTextField.setBackground(Color.red);
            errorTextField.setText("There is no piece to save!");
            resetErrorTextField();
            break;

        case BAD_POSITION:
            errorTextField.setBackground(Color.RED);
            errorTextField.setText("Position must be between 0 - 255!");
            resetErrorTextField();
            break;

        case BAD_ANGLE:
            errorTextField.setBackground(Color.RED);
            errorTextField.setText("Angle must be between 0 and 90!");
            resetErrorTextField();
            break;

        default:
            errorTextField.setBackground(Color.RED);
            errorTextField.setText("An error occurred!");
            resetErrorTextField();
    }
}

public void resetErrorTextField() {
    TimerTask task = new TimerTask() {
        public void run() {
            errorTextField.setText("No errors");
            errorTextField.setBackground(Color.GREEN);
        }
    };

    java.util.Timer currTimer = new Timer();
    currTimer.schedule(task, (long) 5000);
}

@Override
public void keyReleased(KeyEvent e) {}
@Override
public void keyTyped(KeyEvent e) {}

}
