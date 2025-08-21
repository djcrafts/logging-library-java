
/**
 * Logger is the main class for logging messages with different levels and handlers.
 * Supports adding multiple handlers and setting log levels.
 * Thread-safe for handler publishing.
 */
package logger.core;

import logger.handler.Handler;
import logger.model.LogRecord;
import logger.model.AlertMarker;
// ...existing code...
import java.util.Map;

import java.util.ArrayList;
import java.util.List;


/**
 * Logger for logging messages with handlers and levels.
 */
import java.util.HashMap;

public class Logger {

    /** Name of the logger instance. */
    private final String name;
    /** List of handlers to publish log records. */
    private final List<Handler> handlers = new ArrayList<>();
    /** Minimum log level for this logger. */
    private LogLevel level = LogLevel.DEBUG;
    /** Default id for all log records (optional). */
    private String defaultId = null;
    /** Default parameters for all log records (optional). */
    private Map<String, Object> defaultParams = new HashMap<>();

    /**
     * Create a new Logger with the given name.
     * @param name Name of the logger
     */
    public Logger(String name) {
        this.name = name;
        // Load defaults from logging.properties
        try (java.io.InputStream in = getClass().getClassLoader().getResourceAsStream("logging.properties")) {
            if (in != null) {
                java.util.Properties props = new java.util.Properties();
                props.load(in);
                String id = props.getProperty("default.id");
                if (id != null && !id.isEmpty()) {
                    this.defaultId = id;
                }
                Map<String, Object> params = new HashMap<>();
                for (String key : props.stringPropertyNames()) {
                    if (key.startsWith("default.param.")) {
                        String paramKey = key.substring("default.param.".length());
                        params.put(paramKey, props.getProperty(key));
                    }
                }
                if (!params.isEmpty()) {
                    this.defaultParams = params;
                }
            }
        } catch (Exception ignored) {}
    }

    /**
     * Add a handler to this logger (internal use).
     */
    void addHandler(Handler handler) {
        handlers.add(handler);
    }

    /**
     * Set the minimum log level for this logger (internal use).
     */
    void setLevel(LogLevel level) {
        this.level = level;
    }

    /**
     * Get the current log level for this logger (internal use).
     */
    LogLevel getLevel() {
        return level;
    }

    /**
     * Log a message at the given level.
     * @param level LogLevel
     * @param message Message to log
     */
    /**
     * Log a message at the given level, with optional id and parameters.
     * @param level LogLevel
     * @param message Message to log
     * @param id Optional id for the log record
     * @param params Optional custom parameters
     */
    private void log(LogLevel level, String message, String id, Map<String, Object> params) {
        log(level, message, id, params, null);
    }

    /**
     * Log a message at the given level, with optional id, parameters, and alert marker.
     */
    private void log(LogLevel level, String message, String id, Map<String, Object> params, AlertMarker alertMarker) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        String useId = (id != null) ? id : defaultId;
        Map<String, Object> useParams = (params != null && !params.isEmpty()) ? params : defaultParams;
        LogRecord record = new LogRecord(level, message, useId, useParams, alertMarker);
        synchronized (handlers) {
            for (Handler handler : handlers) {
                if (level.ordinal() >= handler.getLevel().ordinal()) {
                    handler.publish(record);
                }
            }
        }
    }

    /**
     * Log a message at the given level (no id/params).
     */
    private void log(LogLevel level, String message) {
        log(level, message, null, null, null);
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

    /**
     * Log an alert message. Handlers can process alerts differently.
     * @param message Message to log
     */
    public void alert(String message) {
        log(LogLevel.ERROR, message, null, null, new AlertMarker("ALERT"));
    }
}
