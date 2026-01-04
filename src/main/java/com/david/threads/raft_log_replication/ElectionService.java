package com.david.threads.raft_log_replication;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ElectionService {

    private final List<RaftNode> nodes;
    private final ClusterState cluster;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    ElectionService(List<RaftNode> nodes, ClusterState cluster) {
        this.nodes = nodes;
        this.cluster = cluster;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(
                this::checkElection,
                100,
                100,
                TimeUnit.MILLISECONDS
        );
    }

    private void checkElection() {
        for (RaftNode node : nodes) {

            if (Role.LEADER.equals(node.getRole())) {
                sendHeartbeats(node);
                continue;
            }

            if (node.electionTimeout(randomTimeout())) {
                System.out.println("Election timeout for " + node.getId());
                tryElect(node);
            }
        }
    }

    private void tryElect(RaftNode candidate) {
        int newTerm = cluster.currentTerm.incrementAndGet();

        if (cluster.votedFor.compareAndSet(-1, candidate.getId())) {
            candidate.becomeLeader(newTerm);
        }
    }

    private void sendHeartbeats(RaftNode leader) {
        for (RaftNode node : nodes) {
            if (node.getId() != leader.getId()) {
                node.heartbeat();
            }
        }
    }

    private long randomTimeout() {
        return ThreadLocalRandom.current().nextLong(300, 600);
    }
}
