#define stepPin 7
#define dirPin 2
int steps;

void setup() {
  pinMode(stepPin, OUTPUT);
  pinMode(dirPin, OUTPUT);  
  Serial.begin(9600);

}

void loop() {

  while (Serial.available() > 0) {
    int command = Serial.parseInt();

    if (command = 1) {

      int direction = Serial.parseInt();

       //set motor direction
      if (direction > 0) {
        pinMode(dirPin, INPUT);
        Serial.print("ONE");


      } else {
        pinMode(dirPin, OUTPUT);
        digitalWrite(dirPin,HIGH);
        Serial.print("Two");
      }

      steps = Serial.parseInt();

      for (int i = 0; i < steps; i++) {
        digitalWrite(stepPin, HIGH);
        delayMicroseconds(1000);
        digitalWrite(stepPin, LOW);
        delayMicroseconds(1000);
      }

    }

  }
}
