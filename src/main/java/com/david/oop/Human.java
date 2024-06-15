package com.david.oop;

import java.util.List;

public abstract class Human {

    public String gender;

    public List<String> phoneNumbers;

    public abstract void bread();

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
