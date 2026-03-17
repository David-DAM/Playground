package com.david.design_patterns.observer;

public class ObserverExample {
    static void main(String[] args) {
        NewsPublisher newsPublisher = new NewsPublisher();

        Observer david = new NewsObserver("David");
        Observer pepe = new NewsObserver("Pepe");

        newsPublisher.register(david);
        newsPublisher.register(pepe);

        newsPublisher.notifyObservers("New design pattern is Observer");
    }
}
