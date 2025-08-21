
/**
 * FileHandler outputs log records to a file.
 */
package logger.handler;

import logger.core.LogLevel;
import logger.formatter.DefaultFormatter;
import logger.formatter.Formatter;
import logger.model.LogRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Handler for writing log records to a file.
 */
public class FileHandler implements Handler {
    private LogLevel level = LogLevel.DEBUG;
    private Formatter formatter = new DefaultFormatter();
    private final String filePath;

    /**
     * Create a FileHandler for the given file path.
     * @param filePath Path to log file
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Publish a log record to the file if level is sufficient.
     * @param record LogRecord to publish
     */
    @Override
    public synchronized void publish(LogRecord record) {
        if (record.getLevel().ordinal() >= level.ordinal()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
                writer.println(formatter.format(record));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
