package com.david.concurrent.raft_log_replication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements Runnable {
    private final AtomicLong tokens;
    private final ScheduledExecutorService executor;

    public TokenBucket() {
        this.tokens = new AtomicLong(10);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void run() {
        executor.scheduleAtFixedRate(() -> {
            long newTokenCount = tokens.updateAndGet(v -> Math.min(Math.abs(v + 1), 10));
            System.out.println("Tokens: " + newTokenCount);
        }, 0, 300, TimeUnit.MILLISECONDS);
    }

    public boolean hasTokens() {
        return tokens.getAndDecrement() > 0;
    }

    public void shutdown() {
        executor.shutdown();
    }
}
