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

        List<Future<Boolean>> futures = new ArrayList<>();

        for (Follower follower : followers) {
            futures.add(executor.submit(() -> {
                boolean success = false;
                while (!success) {
                    success = follower.append(entry);
                    if (!success) {
                        System.out.println("Follower" + follower.getId() + " retrying " + entry);
                        Thread.sleep(50); // retry
                    }
                }
                return true;
            }));
        }

        int acks = 0;
        for (Future<Boolean> future : futures) {
            try {
                if (future.get()) acks++;
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }

        if (acks >= majority()) {
            commitIndex = Math.max(commitIndex, index);
            System.out.println("COMMITTED " + entry);
        } else {
            System.out.println("NOT COMMITTED " + entry);
        }
    }

    private void scheduleAppend(Follower follower, LogEntry entry, AtomicInteger acks) {
        Runnable task = () -> {
            boolean success = follower.append(entry);
            if (success) {
                acks.incrementAndGet();
            } else {
                System.out.println("Follower" + follower.getId() + " retrying " + entry);
                executor.schedule(() -> scheduleAppend(follower, entry, acks), 50, TimeUnit.MILLISECONDS);
            }
        };


//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                boolean success = follower.append(entry);
//                if (success) {
//                    acks.incrementAndGet();
//                } else {
//                    System.out.println("Follower" + follower.getId() + " retrying " + entry);
//                    executor.schedule(this, 50, TimeUnit.MILLISECONDS); // retry programado
//                }
//            }
//        };

        executor.schedule(task, 0, TimeUnit.MILLISECONDS);
    }

    private int majority() {
        return (followers.size() / 2) + 1;
    }

    int getCommitIndex() {
        return commitIndex;
    }

    List<LogEntry> getLog() {
        return new ArrayList<>(log);
    }

    void shutdown() {
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
