package com.fightclub.core.domain.error;

public class PasswordValidationException extends RuntimeException {

    public PasswordValidationException(String message) {super(message); }
}