package com.epam.rd.tasks.zoo.exception;

public class BadAnimalTypeException extends RuntimeException{
    public BadAnimalTypeException(String message) {
        super(message);
    }
}
