package com.david.concurrent.standar_thread;

public class CustomThread extends Thread {

    public CustomThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Start " + getName());
    }

}
