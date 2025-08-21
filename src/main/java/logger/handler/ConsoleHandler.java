
/**
 * ConsoleHandler outputs log records to the system console.
 */
package logger.handler;

import logger.core.LogLevel;
import logger.formatter.DefaultFormatter;
import logger.formatter.Formatter;
import logger.model.LogRecord;


/**
 * Handler for printing log records to the console.
 */
public class ConsoleHandler implements Handler {
    private LogLevel level = LogLevel.DEBUG;
    private Formatter formatter = new DefaultFormatter();

    /**
     * Publish a log record to the console if level is sufficient.
     * @param record LogRecord to publish
     */
    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().ordinal() >= level.ordinal()) {
            System.out.println(formatter.format(record));
        }
    }

    /**
     * Set the minimum log level for this handler.
     * @param level LogLevel to set
     */
    @Override
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    /**
     * Get the current log level for this handler.
     * @return LogLevel
     */
    @Override
    public LogLevel getLevel() {
        return level;
    }

    /**
     * Set the formatter for this handler.
     * @param formatter Formatter to use
     */
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
}
