package com.intenship.order.exception;

import lombok.Data;

@Data
public class InsufficientBalanceException extends Exception {
    private String message;

    public InsufficientBalanceException(String message) {
        this.message = message;
    }
}
