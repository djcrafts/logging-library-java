
/**
 * Handler interface for publishing log records to destinations.
 */
package logger.handler;

import logger.model.LogRecord;
import logger.core.LogLevel;


/**
 * Implement this interface to create custom log handlers.
 */
public interface Handler {
    /**
     * Publish a log record to the destination.
     * @param record LogRecord to publish
     */
    void publish(LogRecord record);

    /**
     * Set the minimum log level for this handler.
     * @param level LogLevel to set
     */
    void setLevel(LogLevel level);

    /**
     * Get the current log level for this handler.
     * @return LogLevel
     */
    LogLevel getLevel();
}
