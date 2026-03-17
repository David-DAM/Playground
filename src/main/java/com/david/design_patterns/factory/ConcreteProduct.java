package com.david.design_patterns.factory;

public class ConcreteProduct implements Product {
    @Override
    public void doWork() {
        System.out.println("ConcreteProduct: Working...");
    }
}
