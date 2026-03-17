package com.david.design_patterns.factory;

public class FactoryExample {
    static void main() {
        //We can create a product using the factory method
        Creator creator = new ConcreteCreator();
        creator.someOperation();
    }
}
