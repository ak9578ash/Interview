package com.interview.preparation.practise.bill_sharing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Expense {
    private final String id;
    private final Double amount;
    private final String description;
    private ExpenseStatus status;
    private final ExpenseGroup expenseGroup;

    public Expense(Double amount  , String description , ExpenseStatus status ,ExpenseGroup expenseGroup ){
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.expenseGroup = expenseGroup;
    }
}
