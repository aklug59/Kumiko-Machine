package View;

import Adapter.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static Adapter.Adapter.getAdapter;


public class GUI implements ActionListener, KeyListener {
    protected static GUI guiInstance = new GUI();
    protected static Adapter guiLocalAdapter = getAdapter();
    protected static JFrame frame = new JFrame();
    protected static JTextField angleTextField = new JTextField(1);
    protected static JTextField positionTextField = new JTextField(1);
    protected static JTextField startingLengthTextField = new JTextField();
    protected static JTextField currLengthTextField = new JTextField("");
    protected static JTextField targetLengthTextField = new JTextField("");
    protected static JTextField errorTextField = new JTextField("");
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
    protected static JLabel projectName = new JLabel("");
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

    public static HashMap<Object, String> componentNames = new HashMap<Object, String>();

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
    }

    public void nudgeAngle(int direction) throws InterruptedException {
        currAngle = Double.parseDouble(angleTextField.getText());
        if (direction < 0) {
            if (currAngle < .50) {
                System.out.println("Manual adjustment only!");
            } else {
                guiLocalAdapter.angleUpdate(currAngle - .5 );
                angleTextField.setText(String.valueOf(currAngle - .5));
                System.out.println("The minus angle button was pressed!");
            }

        } else {
            if (currAngle > 89.50) {
                System.out.println("Manual adjustment only!");
            } else {
                guiLocalAdapter.angleUpdate(currAngle + .5 );
                angleTextField.setText(String.valueOf(currAngle + .5));
                System.out.println("The plus angle button was pressed!");
            }
        }
    }

    public void nudgePosition (int direction) throws InterruptedException {
        currPosition = Integer.parseInt(positionTextField.getText());
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

    public double getCutLength() {
        double postCutPieceLength = strokeLength - (inchPerStep * currPosition);
        return postCutPieceLength;
    }

    public void updateTime() {
        String currTimeString;
        currTime++;
        currTimeString = String.valueOf(currTime);
        pieceTimeTextField.setText(currTimeString);
    }

    public void resetPieceValues() {
        startingLengthTextField.setText("0");
        currLengthTextField.setText("0");
        targetLengthTextField.setText("0");
        pieceTimeTextField.setText("0");
        currTime = 0;
    }

@Override
    public void actionPerformed(ActionEvent e) {
        JComponent currObject = (JComponent) e.getSource();
        String currName = String.valueOf(currObject.getName());

        switch(currName) {
            case "startingLengthTextField":
                startingLength = Double.parseDouble(startingLengthTextField.getText());
                frame.requestFocusInWindow();
                guiLocalAdapter.updatePiece(startingLength, "start");
                break;
            case "targetLengthTextField":
                targetLength = Double.parseDouble(targetLengthTextField.getText());
                frame.requestFocusInWindow();
                guiLocalAdapter.updatePiece(targetLength, "target");
                break;
            case "currLengthTextField":
                errorWarning(currName);
                frame.requestFocusInWindow();
                break;
            case "anglePlusButton":
                try {
                    nudgeAngle(1);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "angleMinusButton":
                try {
                    nudgeAngle(-1);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "positionMinusButton":
                try {
                    nudgePosition(-1);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "positionPlusButton":
                try {
                    nudgePosition(1);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "positionTextField":
                currPosition = Integer.parseInt(positionTextField.getText());
                System.out.println("We got here");
                if (currPosition >= 0 && currPosition <= 255) {
                    try {
                        guiLocalAdapter.updatePosition(currPosition);
                        frame.requestFocusInWindow();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    errorWarning("badPosition");
                }
                    break;
            case "angleTextField":
                currAngle = Double.parseDouble(angleTextField.getText());
                if (currAngle > 90 || currAngle < 0) {
                    errorWarning(currName);
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
        guiLocalAdapter.updatePiece(getCutLength(), "current");
    }

    //Save the current piece and make a new one.
    if (currKey == KeyEvent.VK_N) {
        if (!firstCut) {
            errorWarning("noPiece");
        } else {
            try {
                guiLocalAdapter.savePiece();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            resetPieceValues();
        }
    }
}
@Override
public void keyReleased(KeyEvent e) {}
@Override
public void keyTyped(KeyEvent e) {}

public void errorWarning(String warning) {

    switch(warning) {

        case "currLengthTextField":
            errorTextField.setBackground(Color.RED);
            errorTextField.setText("Do not touch the current length!");
            resetErrorTextField();
            break;

        case "angleTextField":
            errorTextField.setBackground(Color.red);
            errorTextField.setText("Angle must be between 0 - 90!");
            resetErrorTextField();
            break;

        case "noPiece":
            errorTextField.setBackground(Color.red);
            errorTextField.setText("There is no piece to save!");
            resetErrorTextField();

        case "badPosition":
            errorTextField.setBackground(Color.RED);
            errorTextField.setText("Position must be between 0 - 255");
            resetErrorTextField();
    }


}

public void resetErrorTextField() {
    TimerTask task = new TimerTask() {
        public void run() {
            errorTextField.setText("");
            errorTextField.setBackground(Color.WHITE);
        }
    };

    java.util.Timer currTimer = new Timer();
    currTimer.schedule(task, (long) 5000);
    }

}
