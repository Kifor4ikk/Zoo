package ru.kifor4ik.exception;

public class WrongEnterException extends RuntimeException {
    public WrongEnterException(String message) {
        super(message);
    }
}
