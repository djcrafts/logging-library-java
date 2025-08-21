
/**
 * LogRecord represents a single log event with metadata.
 */
package logger.model;


import logger.core.LogLevel;

import java.time.LocalDateTime;


/**
 * Data structure for a log event, including timestamp, level, message, and thread.
 */
import java.util.Map;

/**
 * Data structure for a log event, including timestamp, level, message, thread, and custom parameters.
 */
public class LogRecord {
    /** Optional alert marker for the log record. */
    private final AlertMarker alertMarker;
    /** Timestamp when the log record was created. */
    private final LocalDateTime timestamp;
    /** Log level of the record. */
    private final LogLevel level;
    /** Log message. */
    private final String message;
    /** Name of the thread that created the log record. */
    private final String threadName;
    /** Optional id for the log record. */
    private final String id;
    /** Optional custom parameters for the log record. */
    private final Map<String, Object> params;

    /**
     * Create a new LogRecord with the given level, message, id, parameters, and alert marker.
     * @param level LogLevel
     * @param message Log message
     * @param id Optional id for the log record
     * @param params Optional custom parameters
     * @param alertMarker Optional alert marker
     */
    public LogRecord(LogLevel level, String message, String id, Map<String, Object> params, AlertMarker alertMarker) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.message = message;
        this.threadName = Thread.currentThread().getName();
        this.id = id;
        this.params = params;
        this.alertMarker = alertMarker;
    }

    /**
     * Convenience constructor for non-alert logs.
     */
    public LogRecord(LogLevel level, String message, String id, Map<String, Object> params) {
        this(level, message, id, params, null);
    }
    /**
     * Get the alert marker for the log record.
     * @return AlertMarker or null
     */
    public AlertMarker getAlertMarker() { return alertMarker; }

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

    /**
     * Get the id of the log record.
     * @return String
     */
    public String getId() { return id; }

    /**
     * Get custom parameters for the log record.
     * @return Map of parameters
     */
    public Map<String, Object> getParams() { return params; }
}
