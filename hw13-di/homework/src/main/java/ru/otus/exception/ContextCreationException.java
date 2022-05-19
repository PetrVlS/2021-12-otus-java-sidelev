package ru.otus.exception;

public class ContextCreationException extends RuntimeException{

    public ContextCreationException(String message) {
        super(message);
    }

    public ContextCreationException(String message, Throwable cause){
        super(message, cause);
    }
}

