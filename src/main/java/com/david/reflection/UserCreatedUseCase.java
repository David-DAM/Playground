package com.david.reflection;

public class UserCreatedUseCase implements EventConsumer<UserCreated> {

    public UserCreatedUseCase() {
    }

    @Override
    public void consume(UserCreated record) {
        System.out.println("Consumed record: " + record);
    }
}
