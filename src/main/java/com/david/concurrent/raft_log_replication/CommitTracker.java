package com.david.concurrent.raft_log_replication;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class CommitTracker {

    private final int majority;
    private final AtomicInteger acks;
    private final CompletableFuture<Boolean> committed;

    public CommitTracker(int clusterSize) {
        this.majority = clusterSize / 2 + 1;
        this.acks = new AtomicInteger(1);
        this.committed = new CompletableFuture<>();
    }

    public void ack() {
        if (acks.incrementAndGet() >= majority) {
            committed.complete(true);
        }
    }

    public CompletableFuture<Boolean> getCommitFuture() {
        return committed;
    }
}

