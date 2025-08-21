
/**
 * Example usage of the logging library.
 * Demonstrates logger, handlers, and log levels.
 */

package logger;

import logger.core.Logger;
import logger.core.LoggerFactory;


/**
 * Main class to show how to use the Logger library.
 */

public class LoggerExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggerExample.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.debug("Debug message");
        logger.warn("Warning message");
        logger.error("Error message");
    }
}
