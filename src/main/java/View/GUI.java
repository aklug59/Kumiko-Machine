package View;

import Adapter.Adapter;
import static Adapter.Adapter.getAdapter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI implements ActionListener, KeyListener {
    JFrame frame = new JFrame();
    private Adapter guiLocalAdapter = getAdapter();
    JTextField angleTextField = new JTextField(1);
    JTextField positionTextField = new JTextField(1);
    JTextField startingLengthTextField = new JTextField("Enter Length!");
    JTextField currLengthTextField = new JTextField("");
    JTextField targetLengthTextField = new JTextField("");
    JButton anglePlusButton = new JButton("+ .5°");
    JButton angleMinusButton = new JButton("- .5°");
    JButton positionPlusButton = new JButton("+");
    JButton positionMinusButton = new JButton("-");
    JLabel angleLabel = new JLabel("Current Angle");
    JLabel positionLabel = new JLabel("Current Position");
    JLabel startingLengthLabel = new JLabel("Starting Length");
    JLabel currentLengthLabel = new JLabel("Current Length");
    JLabel targetLengthLabel = new JLabel("Target Length");
    Font boldFont = new Font("BOLD",Font.BOLD, anglePlusButton.getFont().getSize());
    private static GUI guiInstance = new GUI();
    double currAngle = 90;
    int currPosition = 0;
    double startingLength;
    double currLength;
    double targetLength;

    private GUI() {};

    public static GUI getGUI() {
        if (GUI.guiInstance == null) {
            guiInstance = new GUI();
        }
        return guiInstance;
    }

    public static void populateGUI() {

        guiInstance.makeTextfields();
        guiInstance.makeButtons();
        guiInstance.makeLabels();

        guiInstance.makeFrame();

    }

    public void makeFrame() {
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(this);
        frame.add(angleLabel);
        frame.add(angleTextField);
        frame.add(anglePlusButton);
        frame.add(angleMinusButton);


        frame.add(positionLabel);
        frame.add(positionTextField);
        frame.add(positionPlusButton);
        frame.add(positionMinusButton);

        frame.add(startingLengthTextField);
        frame.add(currLengthTextField);
        frame.add(targetLengthTextField);

        frame.add(startingLengthLabel);
        frame.add(currentLengthLabel);
        frame.add(targetLengthLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void makeButtons() {

        // Make angle buttons
        angleMinusButton.setBounds(425,400,65,50);
        anglePlusButton.setBounds(560,400,65,50);

        angleMinusButton.setFont(boldFont);
        anglePlusButton.setFont(boldFont);

        angleMinusButton.addActionListener(guiInstance);
        anglePlusButton.addActionListener(guiInstance);


        // Make position buttons
        positionMinusButton.setBounds(425,300,65,50);
        positionPlusButton.setBounds(560,300,65,50);

        positionMinusButton.setFont(boldFont);
        positionPlusButton.setFont(boldFont);

        positionMinusButton.addActionListener(guiInstance);
        positionPlusButton.addActionListener(guiInstance);

    }

    public void makeTextfields() {
        // Make angle field
        angleTextField.setText(String.valueOf(currAngle));
        angleTextField.setBounds(500,400, 50,50);
        angleTextField.setHorizontalAlignment(JTextField.CENTER);
        angleTextField.addActionListener(guiInstance);
        // Make position field
        positionTextField.setText(String.valueOf(currPosition));
        positionTextField.setBounds(500,300, 50,50);
        positionTextField.setHorizontalAlignment(JTextField.CENTER);
        positionTextField.addActionListener(guiInstance);

        // Make starting length text field
        startingLengthTextField.setText("0");
        startingLengthTextField.setBounds(700,200, 150,50);
        startingLengthTextField.setHorizontalAlignment(JTextField.CENTER);
        startingLengthTextField.addActionListener(guiInstance);

        // Make current length text field
        currLengthTextField.setText("0");
        currLengthTextField.setBounds(700,300, 150,50);
        currLengthTextField.setHorizontalAlignment(JTextField.CENTER);
        currLengthTextField.addActionListener(guiInstance);

        // Make target length text field
        targetLengthTextField.setText("0");
        targetLengthTextField.setBounds(700,400, 150,50);
        targetLengthTextField.setHorizontalAlignment(JTextField.CENTER);
        targetLengthTextField.addActionListener(guiInstance);



    }

    public void makeLabels() {

        angleLabel.setBounds(485,335,100,100);
        angleLabel.setVisible(true);

        positionLabel.setBounds(485,235, 100,100);
        positionLabel.setVisible(true);

        startingLengthLabel.setBounds(725,125,100,100);
        startingLengthLabel.setVisible(true);

        currentLengthLabel.setBounds(725,225,100,100);
        currentLengthLabel.setVisible(true);

        targetLengthLabel.setBounds(725,325,100,100);
        targetLengthLabel.setVisible(true);


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
        double postCutPieceLength = 2.806 - (.010064453125 * currPosition);
        return postCutPieceLength;
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
        System.out.println("You triggered a cut!");
        System.out.println("the currPosition is " + currPosition);
        currLengthTextField.setText(String.valueOf(getCutLength()));
        guiLocalAdapter.updatePiece(getCutLength(), "current");
    }

    //Save the current piece and make a new one.
    if (currKey == KeyEvent.VK_N) {


    }

}
@Override
public void keyReleased(KeyEvent e) {}
@Override
public void keyTyped(KeyEvent e) {}

}
