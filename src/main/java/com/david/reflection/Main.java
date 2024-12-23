package com.david.reflection;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        UserCreated userCreated = new UserCreated();
        //showUserData(userCreated);

        List<EventConsumer<? extends SpecificRecord>> events = new ArrayList<>();
        events.add(new UserCreatedUseCase());
        events.add(new ProductCreatedUseCase());

        Map<String, EventConsumer<? extends SpecificRecord>> eventConsumerMap = events.stream().collect(Collectors.toMap(
                x -> ((ParameterizedType) x.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName(),
                Function.identity()
        ));

        EventConsumer eventConsumerType = eventConsumerMap.get(userCreated.getClass().getName());

        eventConsumerType.consume(userCreated);
    }

    private static void showUserData(UserCreated userCreated) {
        String typeName = userCreated.getClass().getTypeName();

        System.out.println(typeName);

        String simpleName = userCreated.getClass().getSimpleName();

        System.out.println(simpleName);

        String typeNameInterface = userCreated.getClass().getGenericInterfaces()[0].getTypeName();

        System.out.println(typeNameInterface);

        String simpleNameInterface = userCreated.getClass().getGenericInterfaces()[0].getTypeName();

        System.out.println(simpleNameInterface);
    }
}
