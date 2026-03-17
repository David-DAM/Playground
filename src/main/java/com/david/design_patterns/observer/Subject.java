package com.david.design_patterns.observer;

public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObservers(String message);
}
