package com.david.concurrent;

class VolatileFlagDemo {
    volatile static boolean running = true;

    static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            while (running) {
            }
            System.out.println("Stopped");
        });
        t.start();

        Thread.sleep(1000);
        running = false;
    }
}
