public class SingletonPatternDemo {
    public static void main(String[] args) {
        // illegal construct
        // Compile Time Error: The constructor Singleton() is not visible
        // Singleton object = new Singleton();

        // Get the only object available
        Singleton object = Singleton.getInstance();

        // show the message
        object.showMessage();
    }
}
