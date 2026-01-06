package com.david.threads.raft_log_replication;

public class LogEntry {

    private final int index;
    private final String command;
    private boolean committed;

    LogEntry(int index, String command) {
        this.index = index;
        this.command = command;
        this.committed = false;
    }

    public int getIndex() {
        return index;
    }

    public String serialize() {
        return index + "|" + command + "|" + (committed ? "1" : "0");
    }

    public LogEntry deserialize(String line) {
        String[] split = line.split("\\|");
        LogEntry entry = new LogEntry(
                Integer.parseInt(split[0]),
                split[1]
        );
        entry.committed = split[2].equals("1");
        return entry;
    }
}

