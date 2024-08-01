package View;

//FACTORY METHOD TO HOLD ALL BUTTON RELATED COMPONENTS / LOGIC OF THE GUI

public class ButtonFactory {

    /** Make all the buttons associated with the GUI*/
    public static void makeButtons() {

        // Make angle buttons

        GUI.angleMinusButton.setBounds(425,400,65,50);
        GUI.angleMinusButton.setFont(GUI.boldFont);
        GUI.angleMinusButton.addActionListener(GUI.guiInstance);
        GUI.angleMinusButton.setName("angleMinusButton");


        GUI.anglePlusButton.setBounds(560,400,65,50);
        GUI.anglePlusButton.setFont(GUI.boldFont);
        GUI.anglePlusButton.addActionListener(GUI.guiInstance);
        GUI.anglePlusButton.setName("anglePlusButton");


        // Make position buttons

        GUI.positionMinusButton.setBounds(425,300,65,50);
        GUI.positionMinusButton.setFont(GUI.boldFont);
        GUI.positionMinusButton.setName("positionMinusButton");
        GUI.positionMinusButton.addActionListener(GUI.guiInstance);


        GUI.positionPlusButton.setBounds(560,300,65,50);
        GUI.positionPlusButton.setFont(GUI.boldFont);
        GUI.positionPlusButton.setName("positionPlusButton");
        GUI.positionPlusButton.addActionListener(GUI.guiInstance);
    }
}


