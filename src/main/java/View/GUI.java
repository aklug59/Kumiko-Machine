package View;

import Adapter.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import static Adapter.Adapter.getAdapter;


public class GUI implements ActionListener, KeyListener {
    protected static GUI guiInstance = new GUI();
    protected static Adapter guiLocalAdapter = getAdapter();
    protected static JFrame frame = new JFrame();
    protected static JTextField angleTextField = new JTextField(1);
    protected static JTextField positionTextField = new JTextField(1);
    protected static JTextField startingLengthTextField = new JTextField("Enter Length!");
    protected static JTextField currLengthTextField = new JTextField("");
    protected static JTextField targetLengthTextField = new JTextField("");
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
        Object currObject = e.getSource();

        if (currObject == anglePlusButton) {
            try {
                nudgeAngle(1);
                frame.requestFocusInWindow();
            } catch (InterruptedException ex) {
                 throw new RuntimeException(ex);
            }
        } else if (currObject == angleMinusButton) {
            try {
                nudgeAngle(-1);
                frame.requestFocusInWindow();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else if (currObject == positionPlusButton) {
            try {
                nudgePosition(1);
                frame.requestFocusInWindow();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        } else if (currObject == positionMinusButton) {
            try {
                nudgePosition(-1);
                frame.requestFocusInWindow();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        } else if (currObject == positionTextField) {
            currPosition = Integer.parseInt(positionTextField.getText());
            if (currPosition > 0 || currPosition < 255) {
                try {
                    guiLocalAdapter.updatePosition(currPosition);
                    frame.requestFocusInWindow();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        } else if (currObject == angleTextField) {
            currAngle = Double.parseDouble(angleTextField.getText());
            if (currAngle > 90 || currAngle < 0) {
                System.out.println("Enter a number between 0 and 90!");
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
        } else if (currObject == startingLengthTextField) {
            startingLength = Double.parseDouble(startingLengthTextField.getText());
            frame.requestFocusInWindow();
            guiLocalAdapter.updatePiece(startingLength, "start");
        } else if (currObject == currLengthTextField) {
            System.out.println("Do not touch!");
            frame.requestFocusInWindow();
        } else if (currObject == targetLengthTextField) {
            targetLength = Double.parseDouble(targetLengthTextField.getText());
            frame.requestFocusInWindow();
            guiLocalAdapter.updatePiece(targetLength, "target");
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
            System.out.println("There is no piece to save!");
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

}
