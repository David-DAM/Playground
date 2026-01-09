package com.david.concurrent.standar_thread;

public class StandardThreadDemo {

    static void main(String[] args) throws InterruptedException {
        Thread t1 = new CustomThread("first");
        Thread t2 = new CustomThread("second");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Threads finished");
    }
}
