package View;

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

        GUI.pieceTimeTextField.setBounds(200,400, 150,50);

        GUI.timeLabel.setBounds(235, 360,100,50);
        GUI.timeLabel.setVisible(true);
    }
}
