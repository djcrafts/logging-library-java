package logger;

import logger.core.Logger;
import logger.core.LogLevel;
import logger.handler.ConsoleHandler;
import logger.handler.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
    private Logger logger;
    private FileHandler fileHandler;
    private final String logFile = "test.log";

    @BeforeEach
    void setUp() {
        logger = new Logger("TestLogger");
        fileHandler = new FileHandler(logFile);
        fileHandler.setLevel(LogLevel.DEBUG);
        logger.addHandler(fileHandler);
        logger.setLevel(LogLevel.DEBUG);
        // Clean up log file before each test
        File file = new File(logFile);
        if (file.exists()) file.delete();
    }

    @Test
    void testDebugLogging() throws Exception {
        logger.debug("Debug message");
        Thread.sleep(50); // Wait for file write
        String content = Files.readString(Paths.get(logFile));
        assertTrue(content.contains("Debug message"));
    }

    @Test
    void testInfoLogging() throws Exception {
        logger.info("Info message");
        Thread.sleep(50);
        String content = Files.readString(Paths.get(logFile));
        assertTrue(content.contains("Info message"));
    }

    @Test
    void testWarnLogging() throws Exception {
        logger.warn("Warn message");
        Thread.sleep(50);
        String content = Files.readString(Paths.get(logFile));
        assertTrue(content.contains("Warn message"));
    }

    @Test
    void testErrorLogging() throws Exception {
        logger.error("Error message");
        Thread.sleep(50);
        String content = Files.readString(Paths.get(logFile));
        assertTrue(content.contains("Error message"));
    }

    @Test
    void testLogLevelFiltering() throws Exception {
        logger.setLevel(LogLevel.ERROR);
        logger.info("Should not log");
        logger.error("Should log");
        Thread.sleep(50);
        String content = Files.readString(Paths.get(logFile));
        assertFalse(content.contains("Should not log"));
        assertTrue(content.contains("Should log"));
    }
}
