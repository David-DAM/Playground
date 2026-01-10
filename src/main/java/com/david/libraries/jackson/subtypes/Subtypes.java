package com.david.libraries.jackson.subtypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Subtypes {
    static void main() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Command command = new CreateCommand("1", "David", 25, "example@gmail.com");
        String commandJson = objectMapper.writeValueAsString(command);
        System.out.println("Command serialized: " + commandJson);

        Command deserializedCommand = objectMapper.readValue(commandJson, Command.class);
        System.out.println("Command deserialized: " + deserializedCommand.toString());
    }
}
