package com.david.threads.raft_log_replication;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogReplicator implements Runnable {

    private final ScheduledExecutorService scheduler;
    private final CommitTracker tracker;
    private final LogEntry entry;
    private final RaftNode node;
    private int attempts;

    public LogReplicator(
            ScheduledExecutorService scheduler,
            CommitTracker tracker,
            LogEntry entry,
            RaftNode node
    ) {
        this.scheduler = scheduler;
        this.tracker = tracker;
        this.entry = entry;
        this.node = node;
        this.attempts = 0;
    }

    @Override
    public void run() {
        scheduler.schedule(() -> {
            attempts++;
            if (attempts >= 3 || tracker.getCommitFuture().isDone()) {
                System.out.println("Failed to replicate entry");
                return;
            }

            boolean appended = node.append(entry);

            if (appended) {
                tracker.ack();
            } else if (!tracker.getCommitFuture().isDone()) {
                System.out.printf("Follower: %d Retry: %d Entry: %s%n", node.getId(), attempts, entry);
                run(); // retry
            }
        }, 50, TimeUnit.MILLISECONDS);
    }
}
