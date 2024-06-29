package View;

import Adapter.Adapter;
import static Adapter.Adapter.getAdapter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUI implements ActionListener {
    JFrame frame = new JFrame();

    private Adapter guiLocalAdapter = getAdapter();
    JTextField angleTextField = new JTextField(1);
    JTextField positionTextField = new JTextField(1);
    JButton anglePlusButton = new JButton("+ .5°");
    JButton angleMinusButton = new JButton("- .5°");
    JButton positionPlusButton = new JButton("+");
    JButton positionMinusButton = new JButton("-");
    JLabel angleLabel = new JLabel("Current Angle");
    JLabel positionLabel = new JLabel("Current Position");
    Font boldFont = new Font("BOLD",Font.BOLD, anglePlusButton.getFont().getSize());
    private static GUI guiInstance = new GUI();
    double currAngle;

    int currPosition;


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
        frame.add(angleLabel);
        frame.add(angleTextField);
        frame.add(anglePlusButton);
        frame.add(angleMinusButton);
        frame.add(positionPlusButton);
        frame.add(positionMinusButton);

        frame.add(positionLabel);
        frame.add(positionTextField);

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
        angleTextField.setText("90");
        angleTextField.setBounds(500,400, 50,50);
        angleTextField.setHorizontalAlignment(JTextField.CENTER);
        angleTextField.addActionListener(guiInstance);
        // Make position field
        positionTextField.setText("0");
        positionTextField.setBounds(500,300, 50,50);
        positionTextField.setHorizontalAlignment(JTextField.CENTER);
        positionTextField.addActionListener(guiInstance);
    }

    public void makeLabels() {

        angleLabel.setBounds(485,335,100,100);
        angleLabel.setVisible(true);

        positionLabel.setBounds(485,235, 100,100);
        positionLabel.setVisible(true);
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
            guiLocalAdapter.positionUpdate(currPosition + 5);
            positionTextField.setText(String.valueOf(currPosition + 5));
        } else {
            guiLocalAdapter.positionUpdate(currPosition - 5);
            positionTextField.setText(String.valueOf(currPosition - 5));
        }
    }


    public void actionPerformed(ActionEvent e) {

         Object currObject = e.getSource();

             if (currObject == anglePlusButton) {
                 try {
                     nudgeAngle(1);
                 } catch (InterruptedException ex) {
                     throw new RuntimeException(ex);
                 }
             } else if (currObject == angleMinusButton) {
                try {
                    nudgeAngle(-1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
             } else if (currObject == positionPlusButton) {
                 try {
                     nudgePosition(1);
                 } catch (InterruptedException ex) {
                     throw new RuntimeException(ex);
                 }

             } else if (currObject == positionMinusButton) {
                 try {
                     nudgePosition(-1);
                 } catch (InterruptedException ex) {
                     throw new RuntimeException(ex);
                 }

             } else if (currObject == angleTextField) {
                 currAngle = Double.parseDouble(angleTextField.getText());
                 if (currAngle > 90 || currAngle < 0) {
                     System.out.println("Enter a number between 0 and 90!");
                 } else {
                     angleTextField.getText();
                     try {
                         guiLocalAdapter.angleUpdate(currAngle);
                     } catch (InterruptedException ex) {
                         throw new RuntimeException(ex);
                     }
                 }
             }
    }

}
