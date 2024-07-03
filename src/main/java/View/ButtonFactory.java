package View;

import javax.swing.*;

public class ButtonFactory {

    public static void makeButtons() {
// Make angle buttons
        GUI.angleMinusButton.setBounds(425,400,65,50);
        GUI.anglePlusButton.setBounds(560,400,65,50);

        GUI.angleMinusButton.setFont(GUI.boldFont);
        GUI.anglePlusButton.setFont(GUI.boldFont);

        GUI.angleMinusButton.addActionListener(GUI.guiInstance);
        GUI.anglePlusButton.addActionListener(GUI.guiInstance);


        // Make position buttons
        GUI.positionMinusButton.setBounds(425,300,65,50);
        GUI.positionPlusButton.setBounds(560,300,65,50);

        GUI.positionMinusButton.setFont(GUI.boldFont);
        GUI.positionPlusButton.setFont(GUI.boldFont);

        GUI.positionMinusButton.addActionListener(GUI.guiInstance);
        GUI.positionPlusButton.addActionListener(GUI.guiInstance);

    }
}

