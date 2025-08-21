package logger;

import logger.core.LogLevel;
import logger.handler.ConsoleHandler;
import logger.handler.FileHandler;
import logger.model.LogRecord;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    @Test
    void testConsoleHandlerLevel() {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(LogLevel.WARN);
        assertEquals(LogLevel.WARN, handler.getLevel());
    }

    @Test
    void testFileHandlerWritesToFile() throws Exception {
        String logFile = "handler_test.log";
        FileHandler handler = new FileHandler(logFile);
        handler.setLevel(LogLevel.DEBUG);
        LogRecord record = new LogRecord(LogLevel.DEBUG, "Handler test message");
        handler.publish(record);
        Thread.sleep(50);
        String content = Files.readString(Paths.get(logFile));
        assertTrue(content.contains("Handler test message"));
        // Clean up
        File file = new File(logFile);
        if (file.exists()) file.delete();
    }
}
