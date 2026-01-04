package com.david.threads.raft_log_replication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

class RaftNode {

    private final Map<Integer, LogEntry> log = new ConcurrentSkipListMap<>();
    private final int id;

    private final ClusterState cluster;

    private volatile Role role = Role.FOLLOWER;
    private volatile long lastHeartbeat = System.currentTimeMillis();

    RaftNode(int id, ClusterState cluster) {
        this.id = id;
        this.cluster = cluster;
    }

    public List<LogEntry> getLog() {
        return new ArrayList<>(log.values());
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public boolean append(LogEntry entry) {

        if (Math.random() < 0.2) return false;
        log.put(entry.index(), entry);
        System.out.println("Follower: " + id + " appended: " + entry);

        return true;
    }

    void becomeLeader(int term) {
        role = Role.LEADER;
        cluster.leaderId.set(id);
        System.out.println("Node " + id + " became LEADER (term " + term + ")");
    }

    void becomeFollower(int term) {
        role = Role.FOLLOWER;
        cluster.currentTerm.set(term);
        cluster.votedFor.set(-1);
    }

    void heartbeat() {
        lastHeartbeat = System.currentTimeMillis();
    }

    boolean electionTimeout(long timeoutMs) {
        return System.currentTimeMillis() - lastHeartbeat > timeoutMs;
    }
}

