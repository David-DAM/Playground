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

        this.logFileQueue = new LinkedBlockingQueue<>();
        FileLogWriter fileLogWriter = new FileLogWriter(logFileQueue);

        this.fileLogExecutor.submit(fileLogWriter);
    }

    public void receiveCommand(String command) {

        if (!Role.LEADER.equals(self.getRole())) {
            System.out.println("Not leader, rejecting command");
            return;
        }

        int index = logIndex.getAndIncrement();
        LogEntry entry = new LogEntry(index, command);
        System.out.println("\nLeader received: " + entry);

        logMemoryQueue.add(entry);

        boolean wasEntryAdded = logFileQueue.offer(entry);
        System.out.println("Was entry added to log file queue: " + wasEntryAdded);

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
            System.out.println("COMMITTED: " + entry);
        } else {
            System.out.println("NOT COMMITTED: " + entry);
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
                System.out.println("Follower: " + raftNode.getId() + " Retry: " + retries + " Entry: " + entry);
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
    }

    public void printStatus() {
        System.out.println("\nLeader log: " + logMemoryQueue);
        System.out.println("Commit index: " + commitIndex);
        for (int i = 0; i < raftNodes.size(); i++) {
            System.out.println("Follower " + i + " log: " + raftNodes.get(i).getLogMap());
        }
    }
}
