
/**
 * Formatter interface for formatting log records.
 */
package logger.formatter;

import logger.model.LogRecord;


/**
 * Implement this interface to customize log output format.
 */
public interface Formatter {
    /**
     * Format a log record as a string.
     * @param record LogRecord to format
     * @return Formatted string
     */
    String format(LogRecord record);
}
