package com.david.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static final Lock lock = new ReentrantLock();

    static void main(String[] args) {
        try (var executor = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10; i++) {
                executor.submit(ReentrantLockDemo::access);
            }
            executor.shutdown();
        }
    }

    private static void access() {
        System.out.println(Thread.currentThread().getName() + " trying");
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " accessing");
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " released");
    }
}

