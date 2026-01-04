package com.david.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class VirtualThreadExecutorDemo {

    static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            var task1 = executor.submit(() -> {
                System.out.println("Virtual task 1 running");
                Thread.sleep(1000);
                return "Task 1 done";
            });

            var task2 = executor.submit(() -> {
                System.out.println("Virtual task 2 running");
                Thread.sleep(1000);
                return "Task 2 done";
            });

            System.out.println(task1.get());
            System.out.println(task2.get());

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

