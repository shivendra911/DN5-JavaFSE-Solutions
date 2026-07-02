// Singleton Pattern - Logger class
// Only one instance of this class should exist throughout the application

public class Logger {

    // private static instance - only one will be created
    private static Logger instance;

    // private constructor so no one can do new Logger() from outside
    private Logger() {
        System.out.println("Logger initialized.");
    }

    // this method gives back the single instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}