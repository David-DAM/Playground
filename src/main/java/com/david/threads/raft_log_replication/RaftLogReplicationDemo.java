package com.david.threads.raft_log_replication;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class RaftLogReplicationDemo {

    static void main(String[] args) throws InterruptedException {

        ClusterState cluster = new ClusterState();

        List<RaftNode> raftNodes = IntStream.rangeClosed(1, 7).boxed()
                .map(integer -> new RaftNode(integer, cluster))
                .toList();

        ElectionService electionService =
                new ElectionService(raftNodes, cluster);

        electionService.start();

        Thread.sleep(2000);

        RaftNode leaderNode = raftNodes.stream()
                .filter(n -> Role.LEADER.equals(n.getRole()))
                .findFirst()
                .orElseThrow();

        RaftLeader raftLeader = new RaftLeader(
                leaderNode,
                raftNodes.stream()
                        .filter(n -> n != leaderNode)
                        .toList()
        );

        CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down...");

            raftLeader.shutdown();
            electionService.shutdown();
            latch.countDown();

            System.out.println("Shutdown done");
            raftLeader.printStatus();
        }));

        try (ScheduledExecutorService client = Executors.newSingleThreadScheduledExecutor()) {

            System.out.println("Client started");

            client.scheduleAtFixedRate(
                    () -> raftLeader.receiveCommand("CMD_" + System.nanoTime()),
                    0,
                    300,
                    TimeUnit.MILLISECONDS
            );

            latch.await();
        }
    }
}

