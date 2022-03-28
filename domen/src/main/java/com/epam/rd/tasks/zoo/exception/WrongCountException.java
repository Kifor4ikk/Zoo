package com.epam.rd.tasks.zoo.exception;

public class WrongCountException extends RuntimeException{
    public WrongCountException(String message) {
        super(message);
    }
}
