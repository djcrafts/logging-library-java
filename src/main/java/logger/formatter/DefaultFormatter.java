
/**
 * DefaultFormatter provides a standard format for log records.
 */
package logger.formatter;

import logger.model.LogRecord;


/**
 * Formats log records as [timestamp] [thread] [level]: [message]
 */
public class DefaultFormatter implements Formatter {

    /**
     * Format a log record using the default format.
     * @param record LogRecord to format
     * @return Formatted string
     */
    @Override
    public String format(LogRecord record) {
        return String.format("[%s] [%s] [%s]: [%s]",
                record.getTimestamp(),
                record.getThreadName(),
                record.getLevel(),
                record.getMessage());
    }
}
