package com.david.threads.raft_log_replication;

public record LogEntry(int index, String command) {

    public String serialize() {
        return index + ";" + command;
    }

    public static LogEntry deserialize(String line) {
        String[] split = line.split(";");

        return new LogEntry(
                Integer.parseInt(split[0]),
                split[1]
        );
    }
}

