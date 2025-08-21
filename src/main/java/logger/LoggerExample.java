
/**
 * Example usage of the logging library.
 * Demonstrates logger, handlers, and log levels.
 */
package logger;

import logger.core.Logger;
import logger.core.LogLevel;
import logger.handler.ConsoleHandler;
import logger.handler.FileHandler;


/**
 * Main class to show how to use the Logger library.
 */
public class LoggerExample {

    /**
     * Main method to run the logger example.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Logger logger = new Logger("MyAppLogger");

        // Console output with level INFO and above
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(LogLevel.INFO);

        // File output with level DEBUG and above
        FileHandler fileHandler = new FileHandler("application.log");
        fileHandler.setLevel(LogLevel.DEBUG);

        // Add handlers to logger
        logger.addHandler(consoleHandler);
        logger.addHandler(fileHandler);

        // Set logger level to DEBUG (lowest)
        logger.setLevel(LogLevel.DEBUG);

        // Log some messages
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
    }
}
