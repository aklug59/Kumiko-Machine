package CoreLogic;

public interface ArduinoInterface {

    public void openConnection();

    public void stepAmount(int steps);

    public void initial(int x);

    public void closeConnection();

    public void newPosition(int position);

    public String read();

}

