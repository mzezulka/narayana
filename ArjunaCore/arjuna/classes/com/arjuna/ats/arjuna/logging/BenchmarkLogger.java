package com.arjuna.ats.arjuna.logging;

import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class BenchmarkLogger {
    private static final Logger LOGGER = LogManager.getLogger(BenchmarkLogger.class.getName());
    private static final String MSG = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed"
            + " do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim "
            + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    public static void logMessage() {
        LOGGER.info(UUID.randomUUID() + " " + MSG);
    }
}
