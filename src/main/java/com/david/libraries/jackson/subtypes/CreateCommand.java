package com.david.libraries.jackson.subtypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCommand extends Command {

    private String name;
    private int age;
    private String email;

    @JsonCreator
    public CreateCommand(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("age") int age,
            @JsonProperty("email") String email
    ) {
        super(id, CommandType.CREATE);
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateCommand{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + getId() +
                ", type=" + getType() +
                ", email='" + email + '\'' +
                '}';
    }
}
