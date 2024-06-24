




public class testing {
    static TestMethods tester = new TestMethods();
    static MockArduino mockArd = new MockArduino(1,1,1000);



    public static void main(String[] args) throws InterruptedException {
        mockArd.mockStepper();

        //tester.Stepper();

    }
}
