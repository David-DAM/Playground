package com.david.threads.raft_log_replication;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class Leader {

    private final Queue<LogEntry> log = new ConcurrentLinkedQueue<>();
    private final AtomicInteger logIndex = new AtomicInteger(0);
    private final List<Follower> followers;
    private int commitIndex = -1;

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    Leader(List<Follower> followers) {
        this.followers = followers;
    }

    public void receiveCommand(String command) {
        int index = logIndex.getAndIncrement();
        LogEntry entry = new LogEntry(index, command);
        log.add(entry);

        System.out.println("\nLeader received: " + entry);

        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        for (Follower follower : followers) {
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            futures.add(future);
            attemptAppend(follower, entry, future, 3);
        }

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

    private void attemptAppend(Follower follower, LogEntry entry, CompletableFuture<Boolean> result, int retries) {
        executor.execute(() -> {
            if (retries <= 0 || result.isDone()) {
                return;
            }

            boolean success = follower.append(entry);

            if (success) {
                result.complete(true);
            } else {
                System.out.println("Follower: " + follower.getId() + " Retry: " + retries + " Entry: " + entry);
                executor.schedule(
                        () -> attemptAppend(follower, entry, result, retries - 1),
                        50,
                        TimeUnit.MILLISECONDS
                );
            }
        });
    }

    private int majority() {
        return (followers.size() / 2) + 1;
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void printStatus() {
        System.out.println("\nLeader log: " + log);
        System.out.println("Commit index: " + commitIndex);
        for (int i = 0; i < followers.size(); i++) {
            System.out.println("Follower " + i + " log: " + followers.get(i).getLog());
        }
    }
}
