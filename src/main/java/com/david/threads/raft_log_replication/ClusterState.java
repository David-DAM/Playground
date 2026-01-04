package com.david.threads.raft_log_replication;

import java.util.concurrent.atomic.AtomicInteger;

public class ClusterState {

    final AtomicInteger currentTerm = new AtomicInteger(0);
    final AtomicInteger votedFor = new AtomicInteger(-1);
    final AtomicInteger leaderId = new AtomicInteger(-1);

}
