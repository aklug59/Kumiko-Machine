package examples;

import arduino.Arduino;

import java.util.*;


//TESTING METHOD FOR INITIAL TESTING OF ARDUINO / COMPUTER CONNECTION VIA SERIAL.
public class BasicLEDExample {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner ob = new Scanner(System.in);
		Arduino arduino = new Arduino("COM7", 9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
		arduino.openConnection();
		System.out.println("Enter 1 to switch LED on and 0 to switch LED off");
		char input = ob.nextLine().charAt(0);

		while(input != 'n'){
			arduino.serialWrite(input);
			Thread.sleep(800);
			System.out.println(arduino.serialRead());
			input = ob.nextLine().charAt(0);

		}
		ob.close();
		arduino.closeConnection();
		

	}

}
