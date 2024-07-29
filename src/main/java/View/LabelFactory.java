package View;

import java.awt.*;

import static View.GUI.projectNameLabel;
//FACTORY METHOD TO HOLD ALL LABEL RELATED COMPONENTS / LOGIC OF THE GUI

public class LabelFactory {

    public static void makeLabels() {

        GUI.angleLabel.setBounds(485,335,100,100);
        GUI.angleLabel.setVisible(true);

        GUI.positionLabel.setBounds(485,235, 100,100);
        GUI.positionLabel.setVisible(true);

        GUI.startingLengthLabel.setBounds(725,125,100,100);
        GUI.startingLengthLabel.setVisible(true);

        GUI.currentLengthLabel.setBounds(725,225,100,100);
        GUI.currentLengthLabel.setVisible(true);

        GUI.targetLengthLabel.setBounds(725,325,100,100);
        GUI.targetLengthLabel.setVisible(true);

        GUI.timeLabel.setBounds(235, 360,100,50);
        GUI.timeLabel.setVisible(true);

        GUI.progressBarLabel.setBounds(725, 450, 150, 50);
        GUI.progressBarLabel.setVisible(true);

        String currName = GUI.guiLocalAdapter.getProjectName();
        projectNameLabel.setText(currName);
        projectNameLabel.setFont(new Font("BOLD", Font.PLAIN, 30));

        projectNameLabel.setBounds(50,50,300,100);
        projectNameLabel.setVisible(true);

    }
}
