package com.david.design_patterns.factory;

public class ConcreteCreator extends Creator {

    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
