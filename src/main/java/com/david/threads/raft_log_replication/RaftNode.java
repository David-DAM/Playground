package com.david.threads.raft_log_replication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class RaftNode {

    private final Map<Integer, LogEntry> logMap;
    private final int id;
    private final ClusterState cluster;
    private volatile Role role;
    private volatile long lastHeartbeat;

    public RaftNode(int id, ClusterState cluster) {
        this.id = id;
        this.cluster = cluster;
        this.logMap = new ConcurrentSkipListMap<>();
        this.role = Role.FOLLOWER;
        this.lastHeartbeat = System.currentTimeMillis();
    }

    public List<LogEntry> getLogMap() {
        return new ArrayList<>(logMap.values());
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public boolean append(LogEntry entry) {

        if (Math.random() < 0.2) {
            System.out.printf("Follower: %d dropped: %s%n", id, entry);
            return false;
        }
        logMap.put(entry.getIndex(), entry);
        System.out.printf("Follower: %d appended: %s%n", id, entry);

        return true;
    }

    public void becomeLeader(int term) {
        role = Role.LEADER;
        cluster.leaderId.set(id);
        System.out.printf("Node %d became LEADER (term %d)%n", id, term);
    }

    public void becomeFollower(int term) {
        role = Role.FOLLOWER;
        cluster.currentTerm.set(term);
        cluster.votedFor.set(-1);
    }

    public void heartbeat() {
        lastHeartbeat = System.currentTimeMillis();
    }

    public boolean electionTimeout(long timeoutMs) {
        return System.currentTimeMillis() - lastHeartbeat > timeoutMs;
    }
}

