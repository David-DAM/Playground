package com.david.design_patterns.observer;

public class NewsObserver implements Observer {

    private String name;

    public NewsObserver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {

        System.out.printf("Received news to %s: %s%n", name, message);
    }

}