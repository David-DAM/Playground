package com.david.threads.raft_log_replication;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class RaftLeader {

    private final RaftNode self;
    private final AtomicInteger logIndex;
    private int commitIndex;
    private final TokenBucket tokenBucket;
    private final Queue<LogEntry> logMemoryQueue;
    private final List<RaftNode> raftNodes;
    private final FileLogWriter fileLogWriter;
    private final FileCommitWriter fileCommitWriter;
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
        this.fileLogWriter = new FileLogWriter();
        this.fileCommitWriter = new FileCommitWriter();

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

        fileLogWriter.append(entry);

        logMemoryQueue.add(entry);

        CommitTracker tracker = new CommitTracker(raftNodes.size());

        raftNodes.stream()
                .map(node -> new LogReplicator(scheduledExecutor, tracker, entry, node))
                .forEach(LogReplicator::run);

        tracker.getCommitFuture().thenRun(() -> {
            commitIndex = entry.index();
            fileCommitWriter.truncate(commitIndex);
            System.out.printf("COMMITTED: %s%n", entry);
        });

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
