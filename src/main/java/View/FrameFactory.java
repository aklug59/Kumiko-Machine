package View;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static View.GUI.*;

public class FrameFactory {
    static List<Component> compList = new ArrayList<Component>();


    public static void makeFrame() {

        GUI.frame.setSize(1000,1000);
        GUI.frame.setLayout(null);
        GUI.frame.setVisible(true);
        GUI.frame.setFocusable(true);
        GUI.frame.addKeyListener(GUI.guiInstance);
        GUI.frame.add(GUI.angleLabel);
        GUI.frame.add(GUI.angleTextField);
        GUI.frame.add(GUI.anglePlusButton);
        GUI.frame.add(GUI.angleMinusButton);
        GUI.frame.add(pieceTimeTextField);


        GUI.frame.add(GUI.positionLabel);
        GUI.frame.add(GUI.positionTextField);
        GUI.frame.add(GUI.positionPlusButton);
        GUI.frame.add(GUI.positionMinusButton);

        GUI.frame.add(GUI.startingLengthTextField);
        GUI.frame.add(GUI.currLengthTextField);
        GUI.frame.add(GUI.targetLengthTextField);
        GUI.frame.add(errorTextField);

        GUI.frame.add(GUI.startingLengthLabel);
        GUI.frame.add(GUI.currentLengthLabel);
        GUI.frame.add(GUI.targetLengthLabel);
        GUI.frame.add(GUI.timeLabel);
        GUI.frame.add(GUI.projectName);

        GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
