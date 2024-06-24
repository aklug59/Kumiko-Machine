package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JTextField angle = new JTextField(1);
    JButton plusButton = new JButton("+");
    JButton minusButton = new JButton("-");
    Font boldFont = new Font("BOLD",Font.BOLD,plusButton.getFont().getSize());
    static GUI GUI = new GUI();

    public static void createGUI() {

        GUI.makeTextfields();
        GUI.makeButtons();
        GUI.makeFrame();
    }

    public void makeFrame() {
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(angle);
        frame.add(plusButton);
        frame.add(minusButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void makeButtons() {

        plusButton.setBounds(550,400,50,50);
        minusButton.setBounds(450,400,50,50);

        plusButton.setFont(boldFont);
        minusButton.setFont(boldFont);

        plusButton.addActionListener(GUI);
        minusButton.addActionListener(GUI);

    }

    public void makeTextfields() {
        angle.setText("90");
        angle.setBounds(500,400, 50,50);
        angle.setHorizontalAlignment(JTextField.CENTER);
        angle.addActionListener(GUI);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        try {
            if (s.equals("+")) {
                System.out.println("The plus button was pressed!");

            } else if (s.equals("-")) {
                System.out.println("The minus button was pressed!");

            } else if (Integer.valueOf(s) <= 90 && Integer.valueOf(s) >= 0) {
                System.out.println("The angle is " + s);
            }

        } catch (NumberFormatException t) {
            System.out.println("Please enter a number!");
        }

    }

}
