package View;

import javax.swing.*;
import java.awt.*;

public class TextFieldFactory {

    public static void makeTextFields() {

        // Make angle field
        GUI.angleTextField.setText(String.valueOf(GUI.currAngle));
        GUI.angleTextField.setName("angleTextField");
        GUI.angleTextField.setBounds(500,400, 50,50);
        GUI.angleTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.angleTextField.addActionListener(GUI.guiInstance);

        // Make position field
        GUI.positionTextField.setText(String.valueOf(GUI.currPosition));
        GUI.positionTextField.setName("positionTextField");
        GUI.positionTextField.setBounds(500,300, 50,50);
        GUI.positionTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.positionTextField.addActionListener(GUI.guiInstance);

        // Make starting length text field
        GUI.startingLengthTextField.setText("0");
        GUI.startingLengthTextField.setName("startingLengthTextField");
        GUI.startingLengthTextField.setBounds(700,200, 150,50);
        GUI.startingLengthTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.startingLengthTextField.addActionListener(GUI.guiInstance);

        // Make current length text field
        GUI.currLengthTextField.setText("0");
        GUI.currLengthTextField.setName("currLengthTextField");
        GUI.currLengthTextField.setBounds(700,300, 150,50);
        GUI.currLengthTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.currLengthTextField.setEditable(false);
        GUI.currLengthTextField.addActionListener(GUI.guiInstance);

        // Make target length text field
        GUI.targetLengthTextField.setText("0");
        GUI.targetLengthTextField.setName("targetLengthTextField");
        GUI.targetLengthTextField.setBounds(700,400, 150,50);
        GUI.targetLengthTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.targetLengthTextField.addActionListener(GUI.guiInstance);

        //Make piece time text field
        GUI.pieceTimeTextField.setText("0");
        GUI.pieceTimeTextField.setName("pieceTimeTextField");
        GUI.pieceTimeTextField.setBounds(200,400, 150,50);
        GUI.pieceTimeTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.pieceTimeTextField.addActionListener(GUI.guiInstance);

        //Make errorTextField
        GUI.errorTextField.setName("errorTextField");
        GUI.errorTextField.setBounds(400, 100, 250, 50);
        GUI.errorTextField.setHorizontalAlignment(JTextField.CENTER);
        GUI.errorTextField.setBackground(Color.GREEN);
    }
}
