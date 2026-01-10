package com.david.libraries.jackson.subtypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCommand extends Command {

    private String newName;
    private int newAge;
    private String newEmail;

    @JsonCreator
    public UpdateCommand(
            @JsonProperty("id") String id,
            @JsonProperty("new_name") String newName,
            @JsonProperty("new_age") int newAge,
            @JsonProperty("new_email") String newEmail
    ) {
        super(id, CommandType.UPDATE);
        this.newName = newName;
        this.newAge = newAge;
        this.newEmail = newEmail;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public int getNewAge() {
        return newAge;
    }

    public void setNewAge(int newAge) {
        this.newAge = newAge;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    @Override
    public String toString() {
        return "UpdateCommand{" +
                "newName='" + newName + '\'' +
                ", newAge=" + newAge +
                ", id=" + getId() +
                ", type=" + getType() +
                ", newEmail='" + newEmail + '\'' +
                '}';
    }
}
