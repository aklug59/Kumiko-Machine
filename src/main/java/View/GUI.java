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
    JTextField angle = new JTextField(1);
    JButton plusButton = new JButton("+");
    JButton minusButton = new JButton("-");
    JLabel angleLabel = new JLabel("Current Angle");
    Font boldFont = new Font("BOLD",Font.BOLD,plusButton.getFont().getSize());
    private static GUI guiInstance = new GUI();
    double currAngle;


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
        frame.add(angle);
        frame.add(plusButton);
        frame.add(minusButton);
        frame.add(angleLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void makeButtons() {

        plusButton.setBounds(575,400,50,50);
        minusButton.setBounds(425,400,50,50);

        plusButton.setFont(boldFont);
        minusButton.setFont(boldFont);

        plusButton.addActionListener(guiInstance);
        minusButton.addActionListener(guiInstance);

    }

    public void makeTextfields() {
        angle.setText("90");
        angle.setBounds(500,400, 50,50);
        angle.setHorizontalAlignment(JTextField.CENTER);
        angle.addActionListener(guiInstance);
    }

    public void makeLabels() {
        //angle.setBounds(500,400, 50,50);

        angleLabel.setBounds(485,335,100,100);
        angleLabel.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        try {
            if (s.equals("+")) {
                currAngle = Double.parseDouble(angle.getText());
                guiLocalAdapter.angleUpdate(currAngle + .5 );
                angle.setText(String.valueOf(currAngle + .5));
                System.out.println("The plus button was pressed!");

            } else if (s.equals("-")) {
                currAngle = Double.parseDouble(angle.getText());
                guiLocalAdapter.angleUpdate(currAngle - .5 );
                angle.setText(String.valueOf(currAngle - .5));
                System.out.println("The minus button was pressed!");

            } else if (Double.parseDouble(s) <= 90.0 && Double.parseDouble(s) >= 0.0) {
                guiLocalAdapter.angleUpdate(Double.parseDouble(s));
                System.out.println("The angle is " + s);
            }

        } catch (NumberFormatException | InterruptedException t ) {

            System.out.println("Please enter a valid number!");
        }

    }

}
