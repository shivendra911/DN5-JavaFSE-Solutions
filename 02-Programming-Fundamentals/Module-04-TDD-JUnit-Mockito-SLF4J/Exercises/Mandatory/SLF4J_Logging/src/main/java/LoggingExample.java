// Exercise 1: Logging Error Messages and Warning Levels using SLF4J

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {

    // create a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        // different logging levels
        logger.info("Application has started.");

        logger.warn("This is a warning message - something might go wrong.");

        logger.error("This is an error message - something went wrong!");

        logger.debug("This is a debug message - for development only.");

        // simulating a real scenario
        int balance = -500;

        if (balance < 0) {
            logger.error("Account balance is negative: {}", balance);
        } else if (balance < 1000) {
            logger.warn("Account balance is low: {}", balance);
        } else {
            logger.info("Account balance is fine: {}", balance);
        }

        logger.info("Application finished.");
    }
}
