package com.david.threads.raft_log_replication;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class FileLogWriter implements Runnable {
    private final Path path;
    private final BlockingQueue<LogEntry> queue;
    private final List<LogEntry> batch;

    public FileLogWriter(BlockingQueue<LogEntry> queue) {
        this.queue = queue;
        this.path = Paths.get("src/main/resources/raft/log.txt");
        this.batch = new ArrayList<>(50);
    }

    @Override
    public void run() {

        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        )) {
            while (!Thread.currentThread().isInterrupted()) {
                LogEntry first = queue.poll(100, TimeUnit.MILLISECONDS);
                if (first != null) {
                    batch.add(first);
                    queue.drainTo(batch, 49);
                    System.out.printf("Writing to file log: %d elements%n", batch.size());

                    for (LogEntry e : batch) {
                        writer.write(e.toString());
                        writer.newLine();
                    }
                    writer.flush();
                    batch.clear();
                }
            }

        } catch (InterruptedException e) {
            System.out.println("File log writer interrupted");
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.printf("Error writing to file log: %s%n", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
