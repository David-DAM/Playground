package com.david.libraries.jackson.subtypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateCommand.class, name = "CREATE"),
        @JsonSubTypes.Type(value = UpdateCommand.class, name = "UPDATE")
})
public abstract class Command {

    private String id;

    private CommandType type;

    public Command(String id, CommandType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public CommandType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(CommandType type) {
        this.type = type;
    }
}
