package com.david.threads.raft_log_replication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

class Follower {

    private final Map<Integer, LogEntry> log = new ConcurrentSkipListMap<>();
    private final int id;

    Follower(int id) {
        this.id = id;
    }

    public List<LogEntry> getLog() {
        return new ArrayList<>(log.values());
    }

    public int getId() {
        return id;
    }

    public boolean append(LogEntry entry) {

        log.put(entry.index(), entry);
        System.out.println("Follower " + id + " appended " + entry);

        return true;
    }
}

