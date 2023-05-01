package com.fightclub.core.service;

import com.fightclub.core.domain.error.EmailValidationException;
import com.fightclub.core.domain.error.NameValidationException;
import com.fightclub.core.domain.error.PasswordValidationException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    void validateEmail(String email) {

        if (email == null) {
            throw new EmailValidationException("Email is Null");
        }
        if (!email.contains("@")) {
            throw new EmailValidationException("Email doesn't Contains @");
        }
        if (email.length() > 50) {
            throw new EmailValidationException("Email is too Long");
        }
        if (email.length() < 9) {
            throw new EmailValidationException("Email is too Small");
        }
    }

    void validateName(String name) {

        if (name == null) {
            throw new NameValidationException("Name is Null");
        }
        if (name.length() > 20) {
            throw new NameValidationException("Name is too Long");
        }
        if (name.length() < 2) {
            throw new NameValidationException("Name is too Small");
        }
    }

    void validatePassword(String password, String repeatedPassword) {

        passwordValidateCommons(password);

        if (!password.equals(repeatedPassword)) {
            throw new PasswordValidationException("Passwords is Not the same");
        }
    }

    void validatePassword(String password) {

        passwordValidateCommons(password);
    }


    private boolean containsCapitalLetter(String text) {

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private void passwordValidateCommons(String password) {

        if (password == null) {
            throw new PasswordValidationException("Password is Null");
        }
        if (password.length() > 50) {
            throw new PasswordValidationException("Password is too Long");
        }
        if (password.length() < 8) {
            throw new PasswordValidationException("Password is too Small");
        }
        if (!password.contains("!") || !password.contains("?") || !password.contains(",")
                || !password.contains(".") || !password.contains("@") || !password.contains("-")
                || !password.contains("_") || !password.contains("=") || !password.contains("+")
                || !password.contains("#") || !password.contains("$") || !password.contains("%")
                || !password.contains("^") || !password.contains("&") || !password.contains("*")
                || !password.contains("(") || !password.contains(")") || !password.contains("{")
                || !password.contains("}") || !password.contains("[") || !password.contains("]")
                || !password.contains(";") || !password.contains(":") || !password.contains("'")
                || !password.contains("\"") || !password.contains("~") || !containsCapitalLetter(password)) {
            throw new PasswordValidationException("Password Should contain Special Symbol");

        }
    }
}
