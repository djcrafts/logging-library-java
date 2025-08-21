# Logging Library

A simple, extensible Java logging library for console and file output. Supports log levels, custom formatters, and handlers.

## Features
- Log messages with levels: DEBUG, INFO, WARN, ERROR
- Output to console and/or file
- Customizable formatting
- Thread-safe logging
- Easy to extend with your own handlers and formatters

## Usage Example
```java
import logger.core.Logger;
import logger.core.LoggerFactory;

public class MyApp {
	private static final Logger logger = LoggerFactory.getLogger(MyApp.class);

	public static void main(String[] args) {
		logger.info("Application started");
		logger.debug("Debug message");
		logger.warn("Warning message");
		logger.error("Error message");
		// Alert logging
		logger.alert("Critical alert: service down!");
	}
}
```

## Alert Logging & Extensibility
You can log alerts using `logger.alert("message")`. Alerts are flagged in the log output and can be handled by custom handlers (e.g., send to CloudWatch, Prometheus, email, etc.).

To handle alerts differently, implement your own `Handler` and check for `LogRecord.getAlertMarker() != null`.

Example:
```java
public class CloudWatchAlertHandler implements Handler {
	// ...
	public void publish(LogRecord record) {
		if (record.getAlertMarker() != null) {
			// Send to CloudWatch
		} else {
			// Regular log handling
		}
	}
	// ...
}
```


## Custom Parameters & Configurable Format
You can now log with custom parameters and an optional id:

```java
Map<String, Object> params = new HashMap<>();
params.put("userId", "12345");
params.put("action", "login");
logger.info("User logged in", "event-001", params);
```

### Logging Config File (future extension)
You can add a `logging.properties` file in `src/main/resources` to specify the log format and other options. Example stub:

```
# logging.properties
log.format=[%timestamp%] [%thread%] [%level%] [id:%id%]: [%message%] Params: %params%
```
Custom format support can be added by implementing your own `Formatter` and reading config in your handler/formatter classes.

## Extending
- **Formatter**: Implement the `Formatter` interface to customize log output format.
- **Handler**: Implement the `Handler` interface to create new output destinations.

## Project Structure
- `core/`: Logger and log level definitions
- `handler/`: Output handlers (console, file)
- `formatter/`: Formatters for log messages
- `model/`: Log record data structure