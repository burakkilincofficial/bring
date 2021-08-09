package com.bring.project.bring.error.exception;

public class CustomerAlreadyExistException extends CustomerException {
    public CustomerAlreadyExistException(String name) {
        super(name);
    }
}
