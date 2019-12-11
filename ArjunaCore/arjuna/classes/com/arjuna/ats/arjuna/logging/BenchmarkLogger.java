package com.arjuna.ats.arjuna.logging;

import java.util.UUID;
import org.apache.log4j.Logger;

public final class BenchmarkLogger {
    private static final Logger LOGGER = Logger.getLogger(BenchmarkLogger.class.getName());
    private static final String MSG = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed"
            + " do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim "
            + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    public static void logMessage() {
        LOGGER.info(UUID.randomUUID() + " " + MSG);
    }
}
