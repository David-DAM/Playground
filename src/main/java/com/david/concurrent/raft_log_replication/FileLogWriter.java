package com.david.concurrent.raft_log_replication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FileLogWriter implements Runnable {
    private final Path path;
    private final BlockingQueue<LogEntry> queue;
    private final List<LogEntry> batch;

    public FileLogWriter() {
        this.queue = new LinkedBlockingQueue<>(1000);
        this.path = Paths.get("src/main/resources/raft/log.txt");
        this.batch = new ArrayList<>(50);
    }

    public void append(LogEntry entry) {
        boolean wasEntryAdded;
        try {
            wasEntryAdded = queue.offer(entry, 100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.printf("Interrupted while waiting for log file queue to accept entry: %s%n", entry);
            throw new RuntimeException(e);
        }
        if (!wasEntryAdded) {
            System.out.printf("Log file queue full, dropping entry: %s%n", entry);
        }
    }

    @Override
    public void run() {

        long lastFlush = System.currentTimeMillis();

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

                    for (LogEntry e : batch) {
                        writer.write(e.serialize());
                        writer.newLine();
                    }

                    long now = System.currentTimeMillis();
                    if (batch.size() >= 50 || now - lastFlush > 100) {
                        System.out.printf("Writing to file log: %d elements%n", batch.size());
                        writer.flush();
                        lastFlush = now;
                    }

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

    private List<LogEntry> load() {

        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(LogEntry::deserialize).toList();
        } catch (IOException e) {
            System.out.println("Error recovering log file");
            throw new RuntimeException(e);
        }
    }
}
