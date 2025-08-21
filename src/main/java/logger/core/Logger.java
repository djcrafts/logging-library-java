
/**
 * Logger is the main class for logging messages with different levels and handlers.
 * Supports adding multiple handlers and setting log levels.
 * Thread-safe for handler publishing.
 */
package logger.core;

import logger.handler.Handler;
import logger.model.LogRecord;

import java.util.ArrayList;
import java.util.List;


/**
 * Logger for logging messages with handlers and levels.
 */
public class Logger {

    /** Name of the logger instance. */
    private final String name;
    /** List of handlers to publish log records. */
    private final List<Handler> handlers = new ArrayList<>();
    /** Minimum log level for this logger. */
    private LogLevel level = LogLevel.DEBUG;

    /**
     * Create a new Logger with the given name.
     * @param name Name of the logger
     */
    public Logger(String name) {
        this.name = name;
    }

    /**
     * Add a handler to this logger.
     * @param handler Handler to add
     */
    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    /**
     * Set the minimum log level for this logger.
     * @param level LogLevel to set
     */
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    /**
     * Get the current log level for this logger.
     * @return LogLevel
     */
    public LogLevel getLevel() {
        return level;
    }

    /**
     * Log a message at the given level.
     * @param level LogLevel
     * @param message Message to log
     */
    private void log(LogLevel level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        LogRecord record = new LogRecord(level, message);
        synchronized (handlers) {
            for (Handler handler : handlers) {
                if (level.ordinal() >= handler.getLevel().ordinal()) {
                    handler.publish(record);
                }
            }
        }
    }

    /**
     * Log a debug message.
     * @param message Message to log
     */
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Log an info message.
     * @param message Message to log
     */
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Log a warning message.
     * @param message Message to log
     */
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Log an error message.
     * @param message Message to log
     */
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
