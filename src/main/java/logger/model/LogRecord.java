
/**
 * LogRecord represents a single log event with metadata.
 */
package logger.model;


import logger.core.LogLevel;

import java.time.LocalDateTime;


/**
 * Data structure for a log event, including timestamp, level, message, and thread.
 */
public class LogRecord {
    /** Timestamp when the log record was created. */
    private final LocalDateTime timestamp;
    /** Log level of the record. */
    private final LogLevel level;
    /** Log message. */
    private final String message;
    /** Name of the thread that created the log record. */
    private final String threadName;

    /**
     * Create a new LogRecord with the given level and message.
     * @param level LogLevel
     * @param message Log message
     */
    public LogRecord(LogLevel level, String message) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.message = message;
        this.threadName = Thread.currentThread().getName();
    }

    /**
     * Get the timestamp of the log record.
     * @return LocalDateTime
     */
    public LocalDateTime getTimestamp() { return timestamp; }

    /**
     * Get the log level of the record.
     * @return LogLevel
     */
    public LogLevel getLevel() { return level; }

    /**
     * Get the log message.
     * @return String
     */
    public String getMessage() { return message; }

    /**
     * Get the name of the thread that created the record.
     * @return String
     */
    public String getThreadName() { return threadName; }
}
