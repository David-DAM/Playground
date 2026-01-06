package com.david.threads.raft_log_replication;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RaftLeader {

    private final RaftNode self;
    private final AtomicInteger logIndex;
    private int commitIndex;
    private final TokenBucket tokenBucket;
    private final Queue<LogEntry> logMemoryQueue;
    private final List<RaftNode> raftNodes;
    private final BlockingQueue<LogEntry> logFileQueue;
    private final ScheduledExecutorService scheduledExecutor;
    private final ExecutorService fileLogExecutor;

    RaftLeader(RaftNode self, List<RaftNode> raftNodes) {
        this.self = self;
        this.raftNodes = raftNodes;
        this.logIndex = new AtomicInteger(0);
        this.commitIndex = -1;
        this.logMemoryQueue = new ConcurrentLinkedQueue<>();
        this.scheduledExecutor = Executors.newScheduledThreadPool(5);
        this.fileLogExecutor = Executors.newSingleThreadExecutor();
        this.tokenBucket = new TokenBucket();

        this.logFileQueue = new LinkedBlockingQueue<>(1000);
        FileLogWriter fileLogWriter = new FileLogWriter(logFileQueue);

        this.fileLogExecutor.submit(fileLogWriter);
        this.tokenBucket.run();
    }

    public void receiveCommand(String command) {

        if (!Role.LEADER.equals(self.getRole())) {
            System.out.println("Not leader, rejecting command");
            return;
        }

        if (!tokenBucket.hasTokens()) {
            System.out.println("Not enough tokens, rejecting command");
            return;
        }

        int index = logIndex.getAndIncrement();
        LogEntry entry = new LogEntry(index, command);
        System.out.println("Leader received: " + entry);

        boolean wasEntryAdded;
        try {
            wasEntryAdded = logFileQueue.offer(entry, 100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.printf("Interrupted while waiting for log file queue to accept entry: %s%n", entry);
            throw new RuntimeException(e);
        }
        if (!wasEntryAdded) {
            System.out.printf("Log file queue full, dropping entry: %s%n", entry);
        }

        logMemoryQueue.add(entry);

        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        raftNodes.forEach(raftNode -> {
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            futures.add(future);
            attemptAppend(raftNode, entry, future, 3);
        });

        long deadline = System.currentTimeMillis() + 500;

        long acks = futures.stream()
                .map(f -> f.completeOnTimeout(false, deadline - System.currentTimeMillis(), TimeUnit.MILLISECONDS))
                .mapToLong(f -> f.join() ? 1 : 0)
                .sum() + 1;

        if (acks >= majority()) {
            commitIndex = Math.max(commitIndex, index);
            System.out.printf("COMMITTED: %s%n", entry);
        } else {
            System.out.printf("NOT COMMITTED: %s%n", entry);
        }
    }

    private void attemptAppend(RaftNode raftNode, LogEntry entry, CompletableFuture<Boolean> result, int retries) {
        scheduledExecutor.execute(() -> {
            if (retries <= 0 || result.isDone()) {
                return;
            }

            boolean success = raftNode.append(entry);

            if (success) {
                result.complete(true);
            } else {
                System.out.printf("Follower: %d Retry: %d Entry: %s%n", raftNode.getId(), retries, entry);
                scheduledExecutor.schedule(
                        () -> attemptAppend(raftNode, entry, result, retries - 1),
                        50,
                        TimeUnit.MILLISECONDS
                );
            }
        });
    }

    private int majority() {
        return (raftNodes.size() / 2) + 1;
    }

    public void shutdown() {
        scheduledExecutor.shutdown();
        fileLogExecutor.shutdown();
        tokenBucket.shutdown();
    }

    public void printStatus() {
        System.out.printf("Leader log: %s%n", logMemoryQueue);
        System.out.printf("Commit index: %d%n", commitIndex);
        for (int i = 0; i < raftNodes.size(); i++) {
            System.out.printf("Follower %d log: %s%n", i, raftNodes.get(i).getLogMap());
        }
    }
}
