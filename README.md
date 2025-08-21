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
Logger logger = new Logger("MyAppLogger");
ConsoleHandler consoleHandler = new ConsoleHandler();
consoleHandler.setLevel(LogLevel.INFO);
FileHandler fileHandler = new FileHandler("application.log");
fileHandler.setLevel(LogLevel.DEBUG);
logger.addHandler(consoleHandler);
logger.addHandler(fileHandler);
logger.setLevel(LogLevel.DEBUG);
logger.debug("Debug message");
logger.info("Info message");
logger.warn("Warning message");
logger.error("Error message");
```

## Extending
- **Formatter**: Implement the `Formatter` interface to customize log output format.
- **Handler**: Implement the `Handler` interface to create new output destinations.

## Project Structure
- `core/`: Logger and log level definitions
- `handler/`: Output handlers (console, file)
- `formatter/`: Formatters for log messages
- `model/`: Log record data structure

## License
MIT
