package com.david.threads.raft_log_replication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileCommitWriter {
    private final Path path;

    public FileCommitWriter() {
        this.path = Paths.get("src/main/resources/raft/commit.txt");
    }

    public synchronized void truncate(int index) {
        try {
            Files.writeString(
                    path,
                    "commitIndex=" + index,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int load() {
        if (!Files.exists(path)) return -1;

        try {
            String line = Files.readString(path);
            return Integer.parseInt(line.split("=")[1]);
        } catch (Exception e) {
            return -1;
        }
    }
}
