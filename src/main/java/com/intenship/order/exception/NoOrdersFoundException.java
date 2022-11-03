package com.intenship.order.exception;

import lombok.Data;

@Data
public class NoOrdersFoundException extends Exception {

    private String message;

    public NoOrdersFoundException(String message) {
        this.message = message;
    }
}
