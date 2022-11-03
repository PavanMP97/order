package com.intenship.order.exception;

import lombok.Getter;

public class UserNotFoundException extends Exception {

    @Getter
    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
}
