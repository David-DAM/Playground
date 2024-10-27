package com.david.threads;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        
    }

    private static void asyncTasks() {

        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {

            System.out.println("Running task 1");

            for (int i = 0; i < 10; i++) {
                System.out.println("Background task: " + i);
            }

            System.out.println("Task 1 completed");
        });

        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {

            System.out.println("Running task 2");

            System.out.println("Task 2 completed");
        });

        CompletableFuture.allOf(task1, task2).join();
    }

    private static void standardThread() {
        CustomThread customThread1 = new CustomThread("first");
        CustomThread customThread2 = new CustomThread("second");

        customThread1.start();
        customThread2.start();

        try {
            customThread1.join();
            customThread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
