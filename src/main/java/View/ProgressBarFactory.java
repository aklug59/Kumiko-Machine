package View;

public class ProgressBarFactory {
    static final int BAR_MINIMUM = 0;
    static final int BAR_MAXIMUM = 100;

    public static void makeProgressBars() {
        GUI.progressBar.setMinimum(BAR_MINIMUM);
        GUI.progressBar.setMaximum(BAR_MAXIMUM);

        GUI.progressBar.setName("progressBar");
        GUI.progressBar.setBounds(700,500, 150,50);
        //GUI.progressBar.setHorizontalAlignment(JTextField.CENTER);
    }

    public static void updateBar(int newValue) {
        GUI.progressBar.setValue(newValue);
    }
}
