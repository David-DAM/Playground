package com.david.design_patterns.chain_responsibility;

public class UserValidatorChain {

    public static Handler build() {
        var email = new EmailValidator();
        var password = new PasswordLengthValidator();
        var exists = new UserExistsValidator();

        email.setNext(password);
        password.setNext(exists);

        return email;
    }

}
