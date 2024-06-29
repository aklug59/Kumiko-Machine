//Stepper Variables
#define stepPin 7
#define dirPin 2
int steps;
int command;
int stepperCommand = 1;
int direction = 0;


//Actuator Variables
int PWMPin = DAC0;
int theDelay = 10000;
int actuatorCommand = 2;
int position = 0;



void setup() {
  //Actuator Pin
  pinMode(PWMPin, OUTPUT);


  //Stepper Pins & Serial Connection
  pinMode(stepPin, OUTPUT);
  pinMode(dirPin, OUTPUT);  
  Serial.begin(9600);

}

void loop() {

  while (Serial.available() > 0) {
    command = Serial.parseInt();
    
    if (command == stepperCommand) {

      direction = Serial.parseInt();

       //set motor direction
      if (direction > 0) {
        pinMode(dirPin, INPUT);

      } else {
        pinMode(dirPin, OUTPUT);
        digitalWrite(dirPin,HIGH);
      }

      steps = Serial.parseInt();

      for (int i = 0; i < steps; i++) {
        digitalWrite(stepPin, HIGH);
        delayMicroseconds(1000);
        digitalWrite(stepPin, LOW);
        delayMicroseconds(1000);

      }
      
    } else if (command == actuatorCommand) {

      position = Serial.parseInt();

      analogWrite(PWMPin, position);

    }

  }
}
