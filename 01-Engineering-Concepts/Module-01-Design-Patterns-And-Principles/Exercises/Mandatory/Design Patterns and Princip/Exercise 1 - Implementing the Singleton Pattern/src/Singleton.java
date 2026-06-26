public class Singleton {
    // Private static instance of the same class
    private static Singleton instance;

    // Private constructor to restrict instantiation from other classes
    private Singleton() {
    }

    // Public static method that returns the instance of the class
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World! This is a Singleton Pattern.");
    }
}
