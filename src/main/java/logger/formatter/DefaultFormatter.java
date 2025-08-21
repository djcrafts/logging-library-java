
/**
 * DefaultFormatter provides a standard format for log records.
 */
package logger.formatter;


import logger.model.LogRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Formats log records as [timestamp] [thread] [level]: [message]
 */
public class DefaultFormatter implements Formatter {
    private String format = "[%timestamp%] [%thread%] [%level%] [id:%id%]: [%message%] Params: %params%";

    public DefaultFormatter() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("logging.properties")) {
            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                String fmt = props.getProperty("log.format");
                if (fmt != null && !fmt.isEmpty()) {
                    format = fmt;
                }
            }
        } catch (IOException ignored) {}
    }

    /**
     * Format a log record using the default format.
     * @param record LogRecord to format
     * @return Formatted string
     */
    @Override
    public String format(LogRecord record) {
        String result = format;
        result = result.replace("%timestamp%", String.valueOf(record.getTimestamp()));
        result = result.replace("%thread%", record.getThreadName());
        result = result.replace("%level%", String.valueOf(record.getLevel()));
        result = result.replace("%id%", record.getId() != null ? record.getId() : "");
        result = result.replace("%message%", record.getMessage());
        result = result.replace("%params%", record.getParams() != null ? record.getParams().toString() : "");
        if (record.getAlertMarker() != null) {
            result += " [ALERT: " + record.getAlertMarker().type() + "]";
        }
        return result;
    }
}
