package com.bring.project.bring.error.exception;

public class CustomerException extends RuntimeException {
    private final String errorCode;
    private final Object[] args;

    public CustomerException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
        this.args = null;
    }

    public CustomerException(String errorCode, Object... args) {
        super(errorCode);
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
