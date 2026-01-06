package com.david.threads.raft_log_replication;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.BlockingQueue;

public class FileLogWriter implements Runnable {
    private final Path path;
    private final BlockingQueue<LogEntry> queue;

    public FileLogWriter(BlockingQueue<LogEntry> queue) {
        this.queue = queue;
        this.path = Paths.get("src/main/resources/raft/log.txt");
    }

    @Override
    public void run() {

        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            while (!Thread.currentThread().isInterrupted()) {
                LogEntry entry = queue.take();
                System.out.println("Writing to file log: " + entry);
                writer.write(entry.toString());
                writer.newLine();
                writer.flush();
            }

        } catch (InterruptedException e) {
            System.out.println("File log writer interrupted");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("Error writing to file log: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
