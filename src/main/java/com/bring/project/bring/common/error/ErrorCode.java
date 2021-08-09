package com.bring.project.bring.common.error;

public enum ErrorCode {

    CUSTOMER_ENTITY_NOT_FOUND("bring.exception.customer.entity.not.found", "Could not find customer!"),
    CUSTOMER_DB_EXP("bring.exception.customer.db.error", "Error occurred on database while saving customer! You may check duplicate key constraint"),
    CUSTOMER_ALREADY_EXISTS("bring.exception.customer.already.exists", "Customer already exists with same email"),
    BOOK_ENTITY_NOT_FOUND("bring.exception.book.entity.not.found", "Could not find book!"),
    BOOK_DB_EXP("bring.exception.book.db.error", "Error occurred on database while saving book! You may check duplicate key constraint"),
    ORDER_ENTITY_NOT_FOUND("bring.exception.order.entity.not.found", "Could not find order!"),
    ORDER_DB_EXP("bring.exception.order.db.error", "Error occurred on database while saving order! You may check duplicate key constraint"),
    NOT_ENOUGH_AMOUNT_EXCEPTION("bring.exception.book.amount.not", "Check book numbers, it is not allow that amount!"),
    WRONG_AMOUNT_EXCEPTION("bring.exception.order.amount.not", "It is not allowed buy 0 or less than amount");

    private final String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
