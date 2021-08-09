package com.bring.project.bring.common.error.exception;

public class ValidationException extends RuntimeException {
    private final String errorCode;
    private final Object[] args;

    public ValidationException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
        this.args = null;
    }

    public ValidationException(String errorCode, Object... args) {
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
