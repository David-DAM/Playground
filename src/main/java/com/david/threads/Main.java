package com.david.threads;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        
    }

    private static void executorService() {

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            Future<String> task1 = executorService.submit(() -> {
                System.out.println("Running task 1");
                for (int i = 0; i < 10; i++) {
                    System.out.println("Background task 1: " + i);
                }

                return "Task 1 completed";
            });

            Future<String> task2 = executorService.submit(() -> {
                System.out.println("Running task 2");
                for (int i = 0; i < 10; i++) {
                    System.out.println("Background task 2: " + i);
                }

                return "Task 2 completed";
            });


            String task1Result = task1.get();
            String task2Result = task2.get();

            System.out.println(task1Result + " " + task2Result);

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void completableFuture() {

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
