package com.david.threads.raft_log_replication;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class CommitTracker {

    private final int majority;
    private final AtomicInteger acks = new AtomicInteger(1);
    private final CompletableFuture<Boolean> committed = new CompletableFuture<>();

    public CommitTracker(int clusterSize) {
        this.majority = clusterSize / 2 + 1;
    }

    public void ack() {
        if (acks.incrementAndGet() >= majority) {
            committed.complete(true);
        }
    }

    CompletableFuture<Boolean> getCommitFuture() {
        return committed;
    }
}

