package com.david.threads;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
       
    }

    private static void synchronizedThreadPoolExecutor() {

        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {

            for (int i = 0; i < 10; i++)
                executorService.submit(Main::access);

            executorService.shutdown();
        }
    }

    private static synchronized void access() {
        System.out.println("Accessing shared resource");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Finished accessing shared resource");
    }

    private static void lockThreadPoolExecutor() {

        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            Lock lock = new ReentrantLock();
            for (int i = 0; i < 10; i++)
                executorService.submit(() -> accessLock(lock));

            executorService.shutdown();
        }
    }

    private static void accessLock(Lock lock) {
        System.out.println("Trying to access shared resource");
        lock.lock();
        try {
            System.out.println("Accessing shared resource");
            Thread.sleep(1000);
            System.out.println("Finished accessing shared resource");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        System.out.println("Finished trying to access shared resource");
    }

    private static void virtualThreadExecutor() {

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
