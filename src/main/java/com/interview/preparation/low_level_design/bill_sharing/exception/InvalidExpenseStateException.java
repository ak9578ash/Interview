package com.interview.preparation.low_level_design.bill_sharing.exception;

public class InvalidExpenseStateException extends Exception{
    public InvalidExpenseStateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
