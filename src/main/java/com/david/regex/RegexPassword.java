package com.david.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPassword {
    static void main(String[] args) {
        //At least one uppercase letter, one lowercase letter, one digit, one special character and minimum 8 characters
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

        String password = "Password1!";
        Matcher passwordMatcher = passwordPattern.matcher(password);

        System.out.println("The password is valid: " + passwordMatcher.matches());

        String password2 = "password1!";
        Matcher passwordMatcher2 = passwordPattern.matcher(password2);
        System.out.println("The password 2 is valid: " + passwordMatcher2.matches());
    }
}
