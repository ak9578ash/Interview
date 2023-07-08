package com.interview.preparation.low_level_design.bill_sharing.exception;

public class ExpenseSettledException extends Exception{
    public ExpenseSettledException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
