package com.david.reflection;

public class ProductCreatedUseCase implements EventConsumer<ProductCreated> {

    public ProductCreatedUseCase() {
    }

    @Override
    public void consume(ProductCreated record) {
        System.out.println("Consumed record: " + record);
    }
}
