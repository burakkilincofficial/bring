package com.bring.project.bring.error.exception;

public class OrderException extends RuntimeException {
    private final String errorCode;
    private final Object[] args;

    public OrderException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
        this.args = null;
    }

    public OrderException(String errorCode, Object... args) {
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
