package com.interview.preparation.low_level_design.bill_sharing.repository;

import com.interview.preparation.low_level_design.bill_sharing.exception.ExpenseDoesNotExistsException;
import com.interview.preparation.low_level_design.bill_sharing.model.Expense;
import com.interview.preparation.low_level_design.vending_machine.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExpenseRepository {
    public static Map<String, Expense> expenseMap = new HashMap<>();

    public Expense addExpense(Expense expense){
        expenseMap.putIfAbsent(expense.getId() , expense);
        return expense;
    }

    public Expense getExpenseById(String expenseId) throws ExpenseDoesNotExistsException {
        if(!expenseMap.containsKey(expenseId)){
            throw new ExpenseDoesNotExistsException("expense does not exist");
        }
        return expenseMap.get(expenseId);
    }
}
