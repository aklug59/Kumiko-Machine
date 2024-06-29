public class MockArduino {
    int stepPin = 7;
    int dirPin = 2;
    int steps;
    int command;
    int position;
    int stepperCommand = 1;
    int actuatorCommand = 2;
    int direction = 0;
    int numSteps;

    public MockArduino (int command, int direction, int steps, int position) {
        this.command = command;
        this.direction = direction;
        this.steps = steps;
        this.position = position;
    }

    public void mockStepper() {

        if (command == stepperCommand) {
            System.out.println("Stepper command received");

            //set motor direction
            if (direction > 0) {
                System.out.println("The motor direction is up");

            } else {
                System.out.println("The motor direction is down");
            }
            //Iterate over numSteps
            for (int i = 0; i < steps; i++) {
                numSteps++;
            }
            System.out.println("The number of steps is " + numSteps);
            }
        }

    //To fill in once actuator is actually working!
    public void mockActuator() {
        if (command == actuatorCommand) {
            System.out.println("Actuator command recieved");
        }
        System.out.println("The position is " + position);
    }

    }
