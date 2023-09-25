package com.interview.preparation.practise.bill_sharing.exception;

import com.interview.preparation.practise.bill_sharing.model.Expense;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
