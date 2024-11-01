package com.david.reflection;

public interface EventConsumer<T extends SpecificRecord> {

    void consume(T record);
}
