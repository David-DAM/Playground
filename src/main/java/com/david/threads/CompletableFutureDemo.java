package com.david.threads;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    static void main(String[] args) {

        CompletableFuture<Void> task1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("Async task 1");
                    sleep();
                });

        CompletableFuture<Void> task2 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("Async task 2");
                    sleep();
                });

        CompletableFuture.allOf(task1, task2).join();
        System.out.println("All tasks finished");
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}

