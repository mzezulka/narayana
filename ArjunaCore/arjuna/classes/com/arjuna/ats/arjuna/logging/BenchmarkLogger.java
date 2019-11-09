package com.arjuna.ats.arjuna.logging;

import java.io.IOException;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public final class BenchmarkLogger {
    private static final Logger LOGGER = Logger.getLogger("MyLog");
    private static final AtomicInteger NUM_WRITES = new AtomicInteger();
    private static final String FILE_PATH = "/tmp/narayana-benchmark-" + new Timestamp(new Date().getTime()).getTime();
    private static FileHandler FH;
    private static final String MSG = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed"
            + " do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim "
            + "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    private static final int MAX_NUM_WRITES = 1_000_000 / MSG.length();

    static {
        try {
            LOGGER.setLevel(Level.ALL);
            FH = new FileHandler(FILE_PATH);
            FH.setLevel(Level.ALL);
            LOGGER.addHandler(FH);
            SimpleFormatter formatter = new SimpleFormatter();
            FH.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logMessage() {
        LOGGER.info(UUID.randomUUID() + " " + MSG);
        if(NUM_WRITES.getAndIncrement() >= MAX_NUM_WRITES) {
            FH.flush();
            FH.close();
            LOGGER.removeHandler(FH);
            LOGGER.info("Emptying log file...");
            new File(FILE_PATH).delete();
            try {
                FH = new FileHandler(FILE_PATH);
            } catch(IOException ioe) {
                throw new RuntimeException(ioe);
            }
            LOGGER.addHandler(FH);
        }
    }
}
