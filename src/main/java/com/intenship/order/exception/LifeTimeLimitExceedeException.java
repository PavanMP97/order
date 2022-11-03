package com.intenship.order.exception;

import lombok.Data;

@Data
public class LifeTimeLimitExceedeException extends Exception {
    private String message;

    public LifeTimeLimitExceedeException(String message) {
        this.message = message;
    }
}
