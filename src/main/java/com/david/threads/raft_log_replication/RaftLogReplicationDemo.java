package com.david.threads.raft_log_replication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaftLogReplicationDemo {

    static void main(String[] args) {

        List<Follower> followers = List.of(
                new Follower(1),
                new Follower(2),
                new Follower(3)
        );

        Leader leader = new Leader(followers);

        try (ExecutorService clients = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                clients.submit(() -> leader.receiveCommand("CMD_" + finalI));
            }

            clients.shutdown();
            boolean termination = clients.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Termination status: " + termination);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leader.shutdown();

        leader.printStatus();
    }
}

