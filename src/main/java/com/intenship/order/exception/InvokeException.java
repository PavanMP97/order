package com.intenship.order.exception;

import javax.naming.LimitExceededException;


public class InvokeException {

    public static String insufficientBalanceException() {
        try {
            throw new LifeTimeLimitExceedeException("You are exceeded the maximum limit....\n Order cancelled  ");
        } catch (LifeTimeLimitExceedeException e) {
            return e.getMessage();
        }

    }

    public static String limitExceededException() {
        try {
            throw new LimitExceededException("You can only order maximum of 5 product.....!");
        } catch (LimitExceededException e) {
            return e.getMessage();
        }
    }

    public static String userNotFoundException() {
        try {
            throw new UserNotFoundException("User doesn't exist with the given id: ");
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }

    public static String noOrdersFoundException() {
        try {
            throw new NoOrdersFoundException("No Orders Found....!");
        } catch (NoOrdersFoundException e) {
            return e.getMessage();
        }
    }

}
