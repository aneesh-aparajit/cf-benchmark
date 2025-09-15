package dev.kamui;

import java.util.logging.Logger;

public class CfLogger {

    private final Logger logger;

    public CfLogger(String name) {
        this.logger = Logger.getLogger(name);
    }

    public void info(String msg, String traceId) {
        logger.info(String.format("[trace: %s, thread: %s] %s", traceId, Thread.currentThread(), msg));
    }

    public void error(String msg, String traceId) {
        logger.severe(String.format("[trace: %s, thread: %s] %s", traceId, Thread.currentThread(), msg));
    }

}
