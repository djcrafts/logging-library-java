package logger.core;

/**
 * LoggerFactory provides static methods to get logger instances for this logging library.
 * Usage example:
 * <pre>
 *     private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
 *     // ...
 *     logger.info("Hello world!");
 * </pre>
 */
public class LoggerFactory {
    /**
     * Get a logger for the given class.
     * @param clazz Class to associate with logger
     * @return Logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz.getName());
    }

    /**
     * Get a logger for the given name.
     * @param name Logger name
     * @return Logger instance
     */
    public static Logger getLogger(String name) {
        return new Logger(name);
    }
}
