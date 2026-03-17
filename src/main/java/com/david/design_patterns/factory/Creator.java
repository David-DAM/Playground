package com.david.design_patterns.factory;

public abstract class Creator {

    public abstract Product factoryMethod();

    public void someOperation() {
        Product product = factoryMethod();
        product.doWork();
    }

}
