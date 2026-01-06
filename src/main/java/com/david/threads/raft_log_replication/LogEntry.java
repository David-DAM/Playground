package com.david.threads.raft_log_replication;

record LogEntry(int index, String command) {

    @Override
    public String toString() {
        return "{" +
                "\"index\":" + index +
                ",\"command\":\"" + command + "\"" +
                "}";
    }
}

