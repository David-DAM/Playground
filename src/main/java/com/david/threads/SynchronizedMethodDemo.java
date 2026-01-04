package com.david.threads;

public class SynchronizedMethodDemo {

    static void main(String[] args) {
        try (var executor = java.util.concurrent.Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10; i++) {
                executor.submit(SynchronizedMethodDemo::access);
            }
            executor.shutdown();
        }
    }

    private static synchronized void access() {
        System.out.println(Thread.currentThread().getName() + " accessing resource");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}

