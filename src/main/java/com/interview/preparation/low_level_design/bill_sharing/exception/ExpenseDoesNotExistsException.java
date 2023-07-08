package com.interview.preparation.low_level_design.bill_sharing.exception;

public class ExpenseDoesNotExistsException extends Exception {
    public ExpenseDoesNotExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
