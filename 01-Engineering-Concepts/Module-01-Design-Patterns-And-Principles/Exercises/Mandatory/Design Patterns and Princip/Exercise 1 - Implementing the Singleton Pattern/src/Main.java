// Test class to verify Singleton pattern

public class Main {

    public static void main(String[] args) {

        // getting instance two times - should be the same object
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application started");
        logger2.log("User logged in");

        // checking if both refer to same object
        if (logger1 == logger2) {
            System.out.println("Both are same instance - Singleton works!");
        } else {
            System.out.println("Different instances - Singleton failed!");
        }
    }
}